package com.vti.examwebsise.examonline.sercutity.oauth;

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
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired private UserService customerService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		CustomerOAuth2User oauth2User = (CustomerOAuth2User) authentication.getPrincipal();

		String username = oauth2User.getName();
		String clientName = oauth2User.getClientName();

		AuthenticationType authenticationType = getAuthenticationType(clientName);

		User customer = customerService.getByUsername(username);
		if (customer == null) {
			customerService.addNewCustomerUponOAuthLogin(username, authenticationType);
		} else {
			oauth2User.setUsername(customer.getUsername());
			customerService.updateAuthenticationType(customer, authenticationType);
		}

		super.onAuthenticationSuccess(request, response, authentication);
	}

	private AuthenticationType getAuthenticationType(String clientName) {
		if (clientName.equals("Google")) {
			return AuthenticationType.GOOGLE;
		} else if (clientName.equals("Facebook")) {
			return AuthenticationType.FACEBOOK;
		} else {
			return AuthenticationType.DATABASE;
		}
	}

}
