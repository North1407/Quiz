package com.vti.examwebsise.examonline.admin.export.controller;

import com.vti.examwebsise.examonline.admin.exam.service.AdminExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ExportController {
    @Autowired
    private AdminExamService adminExamService;
    @GetMapping("/export_pdf")
    public void exportExam(HttpServletResponse response) throws IOException {

    }
}
