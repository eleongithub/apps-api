package com.syscom.apps.web.service.v1.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.syscom.apps.business.service.CustomerService;
import com.syscom.apps.business.service.LoginService;
import com.syscom.apps.dto.CustomerDTO;
import com.syscom.apps.dto.TokenDTO;
import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.web.enums.EnumAPI;
import com.syscom.apps.web.service.BaseEndPoint;


public class CustomerSOAPEndPoint extends BaseEndPoint{

	@Autowired
	private CustomerService customerService;
	
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
	@WebMethod(operationName="login")
	public TokenDTO login(String authorization) throws BusinessException{
		TokenDTO tokenDTO = loginService.authenticateCustomer(authorization);
		writeLogAPIV1(EnumAPI.LOGIN_CUSTOMER.getKey());
		return tokenDTO;
	}
	
	@WebMethod(operationName="create")
	public void create(@WebParam(name="customer")CustomerDTO customer) throws BusinessException{
		customerService.create(customer);
		writeLogAPIV1(EnumAPI.CREATE_CUSTOMER.getKey());
	}

	@WebMethod(operationName="update")
	public CustomerDTO update(@WebParam(name="customer")CustomerDTO customer) throws BusinessException{
		CustomerDTO customerDTO =  customerService.update(customer);
		writeLogAPIV1(EnumAPI.UPDATE_CUSTOMER.getKey());
		return customerDTO;
	}

}
