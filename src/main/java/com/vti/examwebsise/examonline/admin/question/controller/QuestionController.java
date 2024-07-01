package com.vti.examwebsise.examonline.admin.question.controller;

import com.vti.examwebsise.examonline.admin.level.service.LevelService;
import com.vti.examwebsise.examonline.common.entity.Exam;
import com.vti.examwebsise.examonline.common.entity.Level;
import com.vti.examwebsise.examonline.common.entity.Question;
import com.vti.examwebsise.examonline.common.entity.Topic;
import com.vti.examwebsise.examonline.admin.setting.service.SettingService;
import com.vti.examwebsise.examonline.admin.export.serive.ExamWordExporter;
import com.vti.examwebsise.examonline.admin.question.service.QuestionService;
import com.vti.examwebsise.examonline.admin.topic.service.TopicService;
import com.vti.examwebsise.examonline.user.exam.service.ExamService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class QuestionController {
    private final String defaultRedirectURL = "redirect:/manage/questions";
    private final String prepareExamSessionName = "prepareExam";
    private final String changeStatus = "?status=change";
    @Autowired
    private QuestionService questionService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private LevelService levelService;
    @Autowired
    private SettingService settingService;
    @Autowired
    private ExamService examService;

    @GetMapping("/questions")
    public String getAllQuestions(Model model, String keyword, String status,@NotNull HttpSession session) {
        List<Topic> topics = topicService.getAllTopics();

        Exam exam = (Exam)session.getAttribute(prepareExamSessionName);
        List<Question> questions;
        if (status == null && exam != null && keyword == null) {
            questions = exam.getQuestions();
        } else {
            questions = questionService.getAllQuestions(keyword);
            Exam prepareExam = new Exam();
            prepareExam.setQuestions(questions);
            session.setAttribute(prepareExamSessionName, prepareExam);
        }

        List<Level> levels = levelService.getAllLevels();
        Integer numQ = settingService.getNumberOfQuestion()/5;

        // Lưu danh sách câu hỏi vào session
        model.addAttribute("questions", questions);
        model.addAttribute("numQ", new int[numQ]);
        model.addAttribute("levels", levels);
        model.addAttribute("keyword", keyword);
        model.addAttribute("topics", topics);
        return "admins/questions/questions";
    }

    @PostMapping("/questions/import")
    public String importQuestions(@RequestParam("file") MultipartFile file, RedirectAttributes re) {
        try {
            questionService.importQuestions(file);
        } catch (Exception e) {
            re.addFlashAttribute("dangerMessage", "The file is invalid!");
            return defaultRedirectURL;
        }
        re.addFlashAttribute("message", "The questions have been imported successfully");
        return defaultRedirectURL + changeStatus;
    }

    @GetMapping("/questions/export")
    public void exportQuestions(String keyword, String topicName, String levelName, Integer numQ, HttpServletResponse response, HttpServletRequest request,@NotNull HttpSession session) {
        // Lấy danh sách câu hỏi từ session
        Exam prepareExam = (Exam)session.getAttribute(prepareExamSessionName);
        if(prepareExam == null) {
            prepareExam = new Exam();
        }
        if(numQ == null){
            numQ = settingService.getNumberOfQuestion();
        }
        List<Question> questions = questionService.exportQuestions(prepareExam.getQuestions(), topicName, levelName, keyword);
        questions = questions.subList(0, Math.min(numQ, questions.size()));
        ExamWordExporter exporter = new ExamWordExporter();
        try {
            String uuid = java.util.UUID.randomUUID().toString();
            exporter.exportDocx(questions, topicName, uuid, response);
            prepareExam.setQuestions(questions);
            prepareExam.setUuid(uuid);
            prepareExam.setType("offline");
            prepareExam.setStartTime(new Date());
            prepareExam.setAuthor(request.getUserPrincipal().getName());
            examService.save(prepareExam);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/questions/new")
    public String newQuestion(Model model) {
        List<Topic> topics = topicService.getAllTopics();
        List<Level> levels = levelService.getAllLevels();
        model.addAttribute("question", new Question());
        model.addAttribute("topics", topics);
        model.addAttribute("levels", levels);
        model.addAttribute("title", "New Question");
        return "admins/questions/question_form";
    }

    @GetMapping("/questions/{id}/enabled/{status}")
    public String enableQuestion(@PathVariable("id") Integer id, @PathVariable("status") boolean status, RedirectAttributes re) {
        questionService.enableQuestion(id, status);
        re.addFlashAttribute("message", "The question ID " + id + " has been " + (status ? "enabled" : "disabled") + " successfully");
        return defaultRedirectURL + changeStatus;
    }

    @PostMapping("/questions/save")
    public String saveQuestion(Question question, @RequestParam("answerIDs") String[] answerIds, @RequestParam("answerContents") String[] answerContents,
                               @RequestParam("answerCorrects") String[] ansCorrects, RedirectAttributes re) {
        try {
            questionService.save(question, answerIds, answerContents, ansCorrects);
        }catch (DataIntegrityViolationException e) {
            re.addFlashAttribute("dangerMessage", "The question content is too long!");
            return defaultRedirectURL;
        }
        catch (RuntimeException e) {
            re.addFlashAttribute("dangerMessage", "Some error happened!");
            e.printStackTrace();
            return defaultRedirectURL;
        }
        re.addFlashAttribute("message", "The question has been saved successfully");
        return defaultRedirectURL + changeStatus;
    }

    @GetMapping("/questions/edit/{id}")
    public String editQuestion(@PathVariable("id") Integer id, Model model) {
        Question question = questionService.getQuestionById(id);
        List<Topic> topics = topicService.getAllTopics();
        List<Level> levels = levelService.getAllLevels();
        model.addAttribute("question", question);
        model.addAttribute("topics", topics);
        model.addAttribute("levels", levels);
        model.addAttribute("title", "Edit Question(ID: " + id + ")");
        return "admins/questions/question_form";
    }

    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Integer id, RedirectAttributes re) {
        try {
            questionService.deleteQuestion(id);
        } catch (RuntimeException e) {
            re.addFlashAttribute("dangerMessage", "Question with id " + id + " is used in Exam");
            return defaultRedirectURL;
        }
        re.addFlashAttribute("message", "The question ID " + id + " has been deleted successfully");
        return defaultRedirectURL + changeStatus;
    }
}
