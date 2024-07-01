package com.vti.examwebsise.examonline.admin.account.service;

import com.vti.examwebsise.examonline.common.entity.AuthenticationType;
import com.vti.examwebsise.examonline.common.entity.Role;
import com.vti.examwebsise.examonline.common.entity.User;
import com.vti.examwebsise.examonline.admin.account.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        user.setRole(new Role(2));
        user.setEnabled(true);
        encodePassword(user);
        userRepo.save(user);
    }

    public boolean isUsernameUnique(String username) {
        User user = userRepo.findByUsername(username);
        return user == null;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

    public void enableUser(Integer id, boolean status) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEnabled(status);
        userRepo.save(user);
    }

    public User updateAccount(User userInForm) {
        User userInDB = userRepo.findById(userInForm.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!userInForm.getPassword().isEmpty()) {
            userInDB.setPassword(userInForm.getPassword());
            encodePassword(userInDB);
        }

        if (userInForm.getPhotos() != null) {
            userInDB.setPhotos(userInForm.getPhotos());
        }
        if (userInForm.getRole() != null) {
            userInDB.setRole(userInForm.getRole());
        }
        userInDB.setUsername(userInForm.getUsername());
        return userRepo.save(userInDB);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public User getUserById(Integer id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void addNewCustomerUponOAuthLogin(String name, AuthenticationType authenticationType) {
        User customer = new User();
        customer.setUsername(name);
        customer.setEnabled(true);
        customer.setAuthenticationType(authenticationType);
        customer.setPassword("");
        customer.setRole(new Role(2));

        userRepo.save(customer);
    }

    public void updateAuthenticationType(User customer, AuthenticationType authenticationType) {
        customer.setAuthenticationType(authenticationType);
        userRepo.save(customer);
    }
    public void setInExamStatus(User user, boolean status) {
        user.setInExam(status);
        userRepo.save(user);
    }

    public void save(User user) {
        userRepo.save(user);
    }
}
