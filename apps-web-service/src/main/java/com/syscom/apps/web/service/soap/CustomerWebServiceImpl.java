package com.syscom.apps.web.service.soap;

import javax.jws.WebMethod;

import org.springframework.beans.factory.annotation.Autowired;

import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.service.interfaces.CustomerService;
import com.syscom.apps.web.service.models.CustomerWS;


public class CustomerWebServiceImpl implements CustomerService{

	@Autowired
	private CustomerService customerService;
	
	@WebMethod
	@Override
	public void create(CustomerWS customer) throws BusinessException{
		customerService.create(customer);
	}

	@WebMethod
	@Override
	public CustomerWS update(CustomerWS customer){
		return customerService.update(customer);
	}

	@WebMethod
	@Override
	public void delete(Long id){
		customerService.delete(id);
	}



}
