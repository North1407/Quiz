package com.vti.examwebsise.examonline.admin.account.controller;

import com.vti.examwebsise.examonline.utils.FileUploadUtil;
import com.vti.examwebsise.examonline.common.entity.User;
import com.vti.examwebsise.examonline.admin.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class AccountController {
    @Autowired
    private UserService service;
    @GetMapping("/account")
    public String getAccountPage(HttpServletRequest request, Model model){
        User user = service.getByUsername(request.getUserPrincipal().getName());
        model.addAttribute("user",user);
        return "users/account";
    }
    @PostMapping("/account/update")
    public String saveDetails(User user, RedirectAttributes redirectAttributes,
                              @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhotos(fileName);
            User savedUser = service.updateAccount(user);

            String uploadDir = "user-photos/" + savedUser.getId();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        } else {
            if (user.getPhotos().isEmpty()) user.setPhotos(null);
            service.updateAccount(user);
        }
        redirectAttributes.addFlashAttribute("message", "Your account details have been updated.");

        return "redirect:/account";
    }
}
