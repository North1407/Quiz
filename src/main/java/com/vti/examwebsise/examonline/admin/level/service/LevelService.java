package com.vti.examwebsise.examonline.admin.level.service;

import com.vti.examwebsise.examonline.admin.level.repo.LevelRepository;
import com.vti.examwebsise.examonline.common.entity.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {
    @Autowired
    private LevelRepository levelRepository;
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    public Level getLevel(Integer levelId) {
        return levelRepository.findById(levelId).get();
    }

    public void save(Level level, String levelName) {
        level.setLevel(levelName);
        level.setEnabled(true);
        levelRepository.save(level);
    }

    public void deleteLevel(Integer id) {
        levelRepository.deleteById(id);
    }

    public void enableLevel(Integer id, boolean status) {
        Level level = levelRepository.findById(id).get();
        level.setEnabled(status);
        levelRepository.save(level);
    }
}
