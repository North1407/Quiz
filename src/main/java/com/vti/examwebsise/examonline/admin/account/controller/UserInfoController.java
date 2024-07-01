package com.vti.examwebsise.examonline.admin.account.controller;

import com.vti.examwebsise.examonline.utils.FileUploadUtil;
import com.vti.examwebsise.examonline.admin.account.role.service.RoleService;
import com.vti.examwebsise.examonline.common.entity.User;
import com.vti.examwebsise.examonline.admin.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class UserInfoController {
    private String defaultRedirectURL = "redirect:/manage/users";
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public String viewAllUser(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admins/users/users";
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id,RedirectAttributes re) {
        userService.deleteUser(id);
        re.addFlashAttribute("message", "The user ID " + id + " has been deleted successfully.");
        return defaultRedirectURL;
    }
    @GetMapping("/users/{id}/enabled/{status}")
    public String enableUser(@PathVariable("id") Integer id, @PathVariable("status") boolean status,RedirectAttributes re) {
        userService.enableUser(id, status);
        re.addFlashAttribute("message", "The user ID " + id + " has been " + (status ? "enabled" : "disabled") + " successfully");
        return defaultRedirectURL;
    }
    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.listRoles());
        return "admins/users/user_form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes,
                           @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhotos(fileName);
            User savedUser = userService.updateAccount(user);

            String uploadDir = "user-photos/" + savedUser.getId();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        } else {
            if (user.getPhotos().isEmpty()) user.setPhotos(null);
            userService.updateAccount(user);
        }
        redirectAttributes.addFlashAttribute("message", "User info have been updated.");
        return defaultRedirectURL;
    }
}
