package com.syscom.apps.web.service.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.service.interfaces.CustomerService;
import com.syscom.apps.web.service.models.CustomerWS;

@Component("customerEndPoint")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CustomerEndPoint {

	@Autowired
	private CustomerService customerService;
	
	
	/**
	 * 
	 * Create customer
	 * 
	 * @param customer {@link CustomerWS}
	 * @throws BusinessException {@link BusinessException}
	 */
	@POST
	@Path("/customer")
	public void create(CustomerWS customer) throws BusinessException{
		customerService.create(customer);
	}
	
	
	/**
	 * 
	 * Update customer
	 * 
	 * @param customer {@link CustomerWS}
	 * @return updated customer
	 */
	@PUT
	@Path("/customer")
	public CustomerWS update(CustomerWS customer){
		return customerService.update(customer);
	}
	
	
	/**
	 * 
	 * Delete customer
	 * 
	 * @param id {@link Long}
	 */
	@DELETE
	@Path("/customer/{id}")
	public void delete(@PathParam("id") Long id) {
		customerService.delete(id);
	}
		
}
