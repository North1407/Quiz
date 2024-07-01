package com.vti.examwebsise.examonline.admin.exam.controller;

import com.vti.examwebsise.examonline.common.entity.Exam;
import com.vti.examwebsise.examonline.admin.exam.service.AdminExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class AdminExamController {

    @Autowired
    private AdminExamService adminExamService;

    @GetMapping("/exams")
    public String getAllExamsByUuid(Model model, String keyword) {
        List<Exam> exams;
        if (keyword == null) {
            exams = adminExamService.getAllExams();
        }else{
            exams = adminExamService.getAllExams(keyword);
        }
        model.addAttribute("exams", exams);
        return "admins/exams";
    }
    @GetMapping("/exams/delete/{id}")
    public String deleteExam(@PathVariable("id") Integer id, RedirectAttributes re) {
        adminExamService.deleteExam(id);
        re.addFlashAttribute("message", "The exam ID " + id + " has been deleted successfully.");
        return "redirect:/manage/exams";
    }
    @GetMapping("results/get/{id}")
    public String getResult(@PathVariable("id") Integer id, Model model) {
        Exam exam = adminExamService.get(id);
        if ("offline".equals(exam.getType())){
            model.addAttribute("exam", exam);
            return "admins/results/result";
        }
        model.addAttribute("result", exam);
        return "users/results/result";
    }
}
