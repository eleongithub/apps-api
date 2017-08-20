package com.syscom.apps.web.service.v1.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.syscom.apps.business.service.LoginService;
import com.syscom.apps.dto.TokenDTO;
import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.web.enums.EnumAPI;
import com.syscom.apps.web.service.BaseEndPoint;

@Component("customerLoginEndPoint")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("/")
public class CustomerLoginEndPoint extends BaseEndPoint{

	@Autowired
	private LoginService loginService;
	
	/**
	 * 
	 * Login to get valid token
	 * 
	 * @param authorization {@link String}
	 * @return TokenDto {@link TokenDTO}
	 * @throws BusinessException {@link BusinessException}
	 */
	@POST
	@Path("/")
	public TokenDTO login(String authorization) throws BusinessException{
		TokenDTO tokenDTO = loginService.authenticateCustomer(authorization);
		writeLogAPIV1(EnumAPI.LOGIN_CUSTOMER.getKey());
		return tokenDTO;
	}
	
}
