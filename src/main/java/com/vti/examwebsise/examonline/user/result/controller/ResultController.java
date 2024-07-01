package com.vti.examwebsise.examonline.user.result.controller;

import com.vti.examwebsise.examonline.common.entity.Exam;
import com.vti.examwebsise.examonline.common.entity.User;
import com.vti.examwebsise.examonline.user.exam.controller.ExamController;
import com.vti.examwebsise.examonline.user.exam.service.ExamService;
import com.vti.examwebsise.examonline.admin.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/results")
public class ResultController {
    @Autowired
    ExamService examService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public String getAllResult(Model model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        User user = userService.getByUsername(username);
        if (user.isInExam()) {
            int lastIndex = user.getExams().size() - 1;
            return ExamController.examRedirectURL + user.getExams().get(lastIndex).getId();
        }
        List<Exam> exams = user.getExams();
        model.addAttribute("exams", exams);
        return "users/results/results";
    }

    @GetMapping("/get/{id}")
    public String getResult(@PathVariable("id") Integer id, Model model) {
        Exam exam = examService.get(id);
        model.addAttribute("result", exam);
        model.addAttribute("mark", exam.getMark());
        model.addAttribute("time",exam.getEndTime());

        return "users/results/result";
    }
}
