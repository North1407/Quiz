package com.vti.examwebsise.examonline.sercutity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vti.examwebsise.examonline.common.entity.AuthenticationType;
import com.vti.examwebsise.examonline.common.entity.User;
import com.vti.examwebsise.examonline.admin.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class DatabaseLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired private UserService customerService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
		User customer = userDetails.getUser();

		customerService.updateAuthenticationType(customer, AuthenticationType.DATABASE);

		super.onAuthenticationSuccess(request, response, authentication);
	}


}
