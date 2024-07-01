package com.vti.examwebsise.examonline.admin.level.repo;

import com.vti.examwebsise.examonline.common.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Integer> {

    Level findByLevel(String levelName);
}
