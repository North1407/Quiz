package com.vti.examwebsise.examonline.homecontroller;

import com.vti.examwebsise.examonline.common.entity.User;
import com.vti.examwebsise.examonline.admin.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register_form";
    }

    @PostMapping("/save_user")
    public String createCustomer(User user, Model model) {
        if (userService.isUsernameUnique(user.getUsername())) {
            userService.registerUser(user);
            return "redirect:/login";
        } else {
            model.addAttribute("message", "Username existed!");
            return "users/register_form";
        }
    }
}
