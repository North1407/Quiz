package com.vti.examwebsise.examonline.admin.exam.service;

import com.vti.examwebsise.examonline.admin.exam.repo.ExamRepo;
import com.vti.examwebsise.examonline.common.entity.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminExamService {
    @Autowired
    private ExamRepo examRepo;
    public List<Exam> getAllExams() {
        return examRepo.findAll();
    }
    public List<Exam> getAllExams(String uuid) {
        return examRepo.findAllByUuid(uuid);
    }
    public void deleteExam(Integer id) {
        examRepo.deleteById(id);
    }
    public Exam get(Integer id) {
        return examRepo.getById(id);
    }
}
