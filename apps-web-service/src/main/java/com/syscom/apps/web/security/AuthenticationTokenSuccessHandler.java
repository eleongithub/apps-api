package com.syscom.apps.web.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationTokenSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	public AuthenticationTokenSuccessHandler() {
		super();
	}

}
