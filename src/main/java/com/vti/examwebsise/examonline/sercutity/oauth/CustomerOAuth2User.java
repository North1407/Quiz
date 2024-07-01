package com.vti.examwebsise.examonline.sercutity.oauth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomerOAuth2User implements OAuth2User {
	private String clientName;
	private String username;
	private OAuth2User oauth2User;
	
	public CustomerOAuth2User(OAuth2User user, String clientName) {
		this.oauth2User = user;
		this.clientName = clientName;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oauth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oauth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return oauth2User.getAttribute("name");
	}
	

	public String getUsername() {
		return username != null ? username : oauth2User.getAttribute("name");
	}
	public String getClientName() {
		return clientName;
	}
	
	public void setUsername(String fullName) {
		this.username = fullName;
	}
}
