package com.vti.examwebsise.examonline.user.exam.controller;

import com.vti.examwebsise.examonline.common.entity.Answer;
import com.vti.examwebsise.examonline.common.entity.Exam;
import com.vti.examwebsise.examonline.common.entity.Topic;
import com.vti.examwebsise.examonline.common.entity.User;
import com.vti.examwebsise.examonline.admin.setting.service.SettingService;
import com.vti.examwebsise.examonline.user.exam.service.ExamService;
import com.vti.examwebsise.examonline.admin.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequestMapping("/exam")
@Controller
public class ExamController {
    public static final String examRedirectURL = "redirect:/exam/new/";
    @Autowired
    private ExamService service;
    @Autowired
    private UserService userService;
    @Autowired
    SettingService settingService;

    @GetMapping("/quit")
    public String quitExam(HttpServletRequest request){
        String username = request.getUserPrincipal().getName();
        User user = userService.getByUsername(username);
        userService.setInExamStatus(user, false);
        return "redirect:/exam/topics";
    }
    @GetMapping("/topics")
    public String getAllExamTopic(Model model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.getByUsername(username);
        if (user.isInExam()) {
            int lastIndex = user.getExams().size() - 1;
            return examRedirectURL + user.getExams().get(lastIndex).getId();
        }
        List<Topic> topics = service.getEnabledTopics();
        model.addAttribute("topics", topics);
        return "users/exams/topics";
    }

    @GetMapping("topics/{topicName}")
    public String createNewExamByTopic(@PathVariable("topicName") String topicName, @RequestParam("difficulty") String difficulty, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.getByUsername(username);
        if (user.isInExam()) {
            int lastIndex = user.getExams().size() - 1;
            return examRedirectURL + user.getExams().get(lastIndex).getId();
        }
        Exam savedExam = service.createExam(topicName, "0".equals(difficulty) ? "" : difficulty, username);
        return examRedirectURL + savedExam.getId();
    }

    @GetMapping("/new/{id}")
    public String getExam(@PathVariable("id") Integer id, Model model) {
        Exam exam = service.get(id);
        if (new Date().after(exam.getEndTime())) {
            exam = service.save(id, new ArrayList<>());
            model.addAttribute("result", exam);
            model.addAttribute("mark", exam.getMark());
            model.addAttribute("time", exam.getEndTime());
            return "users/results/result";
        }
        List<String> answerIds = new ArrayList<>();
        model.addAttribute("exam", exam);
        model.addAttribute("answerIds", answerIds);
        model.addAttribute("endTime", exam.getEndTime().getTime());
        return "users/exams/exam-form";
    }

    @PostMapping("/submit")
    public String submitExam(Exam exam, Model model) {
        List<Answer> answers = exam.getAnswers();
        Exam examInDb = service.save(exam.getId(), answers);
        model.addAttribute("result", examInDb);
        model.addAttribute("mark", examInDb.getMark());
        model.addAttribute("time", examInDb.getEndTime());
        return "users/results/result";
    }
}

