package com.vti.examwebsise.examonline.admin.setting.controller;

import com.vti.examwebsise.examonline.admin.setting.service.SettingService;
import com.vti.examwebsise.examonline.common.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class SettingController {
    @Autowired
    private SettingService settingService;

    @GetMapping("/settings")
    public String viewAllSettings(Model model) {
        List<Setting> settings = settingService.getAllSettings();
        model.addAttribute("settings", settings);
        return "admins/settings";
    }

    @PostMapping("/settings/save")
    public String saveAllSettings(@RequestParam("settingIds") String[] ids, @RequestParam("settingValues") String[] values, RedirectAttributes re) {
        settingService.saveAllSettings(ids, values);
        re.addFlashAttribute("message", "The settings have been saved successfully");
        return "redirect:/manage/settings";
    }
}
