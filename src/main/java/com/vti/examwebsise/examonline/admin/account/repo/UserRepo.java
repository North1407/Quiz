package com.vti.examwebsise.examonline.admin.account.repo;

import com.vti.examwebsise.examonline.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
