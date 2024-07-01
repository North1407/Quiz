package com.vti.examwebsise.examonline.sercutity;

import com.vti.examwebsise.examonline.common.entity.User;
import com.vti.examwebsise.examonline.admin.account.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if(user!=null){
            return new MyUserDetails(user);
        }else {
            throw new UsernameNotFoundException("Could not found user with username: "+username);
        }
    }
}