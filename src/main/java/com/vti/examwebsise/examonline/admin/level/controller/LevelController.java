package com.vti.examwebsise.examonline.admin.level.controller;

import com.vti.examwebsise.examonline.admin.level.service.LevelService;
import com.vti.examwebsise.examonline.common.entity.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class LevelController {
    @Autowired
    private LevelService levelService;

    @GetMapping("/levels")
    public String viewAllLevels(Model model) {
        List<Level> levels = levelService.getAllLevels();
        model.addAttribute("levels", levels);
        return "admins/levels";
    }
    @PostMapping("/levels/save")
    public String saveTopic(Integer levelId, String levelName, RedirectAttributes re) {
        if (levelId != null) {
            Level level = levelService.getLevel(levelId);
            levelService.save(level, levelName);
            re.addFlashAttribute("message", "The level has been saved successfully");
            return "redirect:/manage/levels";
        }
        Level level = new Level(levelName);
        levelService.save(level, levelName);
        re.addFlashAttribute("message", "The level has been saved successfully");
        return "redirect:/manage/levels";
    }

    @GetMapping("/levels/edit/{id}")
    public String editLevel(@PathVariable("id") Integer id, RedirectAttributes model) {
        Level level = levelService.getLevel(id);
        model.addFlashAttribute("levelName", level.getLevel());
        model.addFlashAttribute("levelId", level.getId());
        return "redirect:/manage/levels";
    }

    @GetMapping("/levels/delete/{id}")
    public String deleteLevel(Integer id, RedirectAttributes re) {
        levelService.deleteLevel(id);
        re.addFlashAttribute("message", "The level has been deleted successfully");
        return "redirect:/manage/levels";
    }

    @GetMapping("/levels/{id}/enabled/{status}")
    public String enableLevel(@PathVariable("id") Integer id, @PathVariable("status") boolean status, RedirectAttributes re) {
        levelService.enableLevel(id, status);
        re.addFlashAttribute("message", "The level ID " + id + " has been " + (status ? "enabled" : "disabled") + " successfully");
        return "redirect:/manage/levels";
    }
}
