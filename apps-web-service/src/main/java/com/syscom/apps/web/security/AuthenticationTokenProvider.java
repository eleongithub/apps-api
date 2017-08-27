package com.syscom.apps.web.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.syscom.apps.business.service.TokenService;
import com.syscom.apps.dto.TokenDTO;
import com.syscom.apps.dto.referentiel.FunctionDTO;
import com.syscom.apps.model.Token;
import com.syscom.apps.web.utils.Functions;

@Component
public class AuthenticationTokenProvider implements AuthenticationProvider{

	@Autowired
	private TokenService tokenService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String authorization = authentication.getName();
//		Token token = tokenService.findValidToken(authorization);
//		if(token == null){
//			throw new BadCredentialsException("Unvalid token. Unauthorized access.");
//		}
//		
//		List<Function> functions = new ArrayList<>();
//		if( token.getCustomer() != null && token.getCustomer().getRole() != null){
//			functions  = token.getCustomer().getRole().getFunctions(); 
//		}		
//		
//		List<GrantedAuthority> grantedAuthorities = functions.stream().map(function -> new SimpleGrantedAuthority(Functions.ROLE+function.getCode()))
//	                                                                            .collect(Collectors.toList());
//	        
//		JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(authorization, grantedAuthorities);
//		jwtAuthenticationToken.setAuthenticated(true);
//		return jwtAuthenticationToken;
		
		TokenDTO tokenDTO = tokenService.findToken(authorization);
		if(tokenDTO == null){
			throw new BadCredentialsException("Unvalid token. Unauthorized access.");
		}
		
		List<FunctionDTO> functionDTOs = new ArrayList<>();
		if( tokenDTO.getCustomerDTO() != null && tokenDTO.getCustomerDTO().getRoleDTO() != null){
			functionDTOs  = tokenDTO.getCustomerDTO().getRoleDTO().getFunctionDTOs(); 
		}		
		
		List<GrantedAuthority> grantedAuthorities = functionDTOs.stream().map(functionDTO -> new SimpleGrantedAuthority(Functions.ROLE+functionDTO.getCode()))
	                                                                            .collect(Collectors.toList());
	        
		JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(authorization, grantedAuthorities);
		jwtAuthenticationToken.setAuthenticated(true);
		return jwtAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}
	

}
