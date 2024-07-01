package com.vti.examwebsise.examonline.admin.account.role.service;

import com.vti.examwebsise.examonline.common.entity.Role;
import com.vti.examwebsise.examonline.admin.account.role.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;
    public List<Role> listRoles() {
        return roleRepo.findAll();
    }
}
