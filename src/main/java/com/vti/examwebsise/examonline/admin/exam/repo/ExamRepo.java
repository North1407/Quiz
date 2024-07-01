package com.vti.examwebsise.examonline.admin.exam.repo;
import com.vti.examwebsise.examonline.common.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepo extends JpaRepository<Exam,Integer> {
    @Query("SELECT e FROM Exam e WHERE e.uuid LIKE %?1%")
    List<Exam> findAllByUuid(String uuid);
}
