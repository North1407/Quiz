package com.vti.examwebsise.examonline.user.exam.service;

import com.vti.examwebsise.examonline.admin.account.repo.UserRepo;
import com.vti.examwebsise.examonline.admin.exam.repo.ExamRepo;
import com.vti.examwebsise.examonline.admin.question.repo.QuestionRepo;
import com.vti.examwebsise.examonline.admin.question.service.AnswerRepo;
import com.vti.examwebsise.examonline.admin.setting.repo.SettingRepo;
import com.vti.examwebsise.examonline.admin.topic.repo.TopicRepo;
import com.vti.examwebsise.examonline.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ExamService {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    ExamRepo examRepo;
    @Autowired
    TopicRepo topicRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    SettingRepo settingRepo;

    public List<Topic> getTopics() {
        return topicRepo.findAll();
    }

    public List<Topic> getEnabledTopics() {
        return topicRepo.findAllEnabled();
    }

    public Exam createExam(String topicName, String difficulty, String username) {
        User user = userRepo.findByUsername(username);
        user.setInExam(true);

        Integer numberOfQuestion = settingRepo.findByName("Question per exam");
        if (numberOfQuestion == null) {
            numberOfQuestion = 10;
            settingRepo.save(new Setting("Question per exam", numberOfQuestion));
        }
        Integer timePerExam = settingRepo.findByName("Time per exam (s)");
        if (timePerExam == null) {
            timePerExam = 600;
            settingRepo.save(new Setting("Time per exam ()", timePerExam));
        }
        List<Question> questions = questionRepo.getExamQuestion(topicName, difficulty);
        questions.subList(0, Math.min(numberOfQuestion, questions.size()));

        Exam exam = new Exam();
        setExamInfo(exam, topicName, user, timePerExam, questions);

        return examRepo.save(exam);
    }

    private void setExamInfo(Exam exam, String topicName, User user, int timePerExam, List<Question> questions) {
        exam.setTopic(topicRepo.findByName(topicName));
        exam.setStartTime(new Date());
        exam.setEndTime(new Date(exam.getStartTime().getTime() + (timePerExam + 1) * 1000L));
        exam.setMark(0);
        exam.setType("online");
        exam.setQuestions(questions);
        exam.setUser(user);
    }


    public Exam get(Integer id) {
        return examRepo.getById(id);
    }

    public Exam save(Integer id, List<Answer> answers) {
        Exam exam = get(id);
        if(new Date().before(exam.getEndTime())){
            exam.setEndTime(new Date());
        }
        exam.setAnswers(answers);
        int trueCounts = checkTrueAnswer(exam.getQuestions(), answers);
        exam.setMark((float) trueCounts / exam.getQuestions().size() * 10);
        exam.getUser().setInExam(false);
        return examRepo.save(exam);
    }

    private int checkTrueAnswer(List<Question> questions, List<Answer> answers) {
        int trueCounts = 0;
        for (Question question : questions) {
            boolean isCorrect = (new HashSet<>(answers).containsAll(question.getAllAnswers()) && question.getAllNonAnswers().stream()
                    .noneMatch(answers::contains));
            if (isCorrect) {
                trueCounts++;
            }
        }
        return trueCounts;
    }

    public void save(Exam exam) {
        examRepo.save(exam);
    }
}
