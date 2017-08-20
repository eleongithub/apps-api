package com.syscom.apps.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import com.syscom.apps.business.service.TokenService;

@Component
public class AuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	private static final String AUTHORIZATION = "Authorization";

//	@Autowired
//	private TokenService tokenService;

	public AuthenticationTokenFilter() {
		super("/api/**");
	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request,
			HttpServletResponse response) {
		return true;
	}

	protected AuthenticationTokenFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(
				defaultFilterProcessesUrl));

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		String accessToken = request.getHeader(AUTHORIZATION);
		if (accessToken == null) {
			throw new PreAuthenticatedCredentialsNotFoundException(
					"No token found in request headers. Unauthorized access.");
		}

//		Token token = tokenService.findValidToken(accessToken);
//		if (token == null) {
//			throw new PreAuthenticatedCredentialsNotFoundException(
//					"Unvalid token. Unauthorized access.");
//		}

		JwtAuthenticationToken authRequest = new JwtAuthenticationToken(accessToken);
		
		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		SecurityContextHolder.getContext().setAuthentication(authResult);
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	}

}
