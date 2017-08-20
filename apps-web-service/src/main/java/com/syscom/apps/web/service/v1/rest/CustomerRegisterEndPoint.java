package com.syscom.apps.web.service.v1.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.syscom.apps.business.service.CustomerService;
import com.syscom.apps.dto.CustomerDTO;
import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.web.enums.EnumAPI;
import com.syscom.apps.web.service.BaseEndPoint;

@Component("customerRegisterEndPoint")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("/")
public class CustomerRegisterEndPoint extends BaseEndPoint{
	
	@Autowired
	private CustomerService customerService;
	
	
	/**
	 * 
	 * Create customer
	 * 
	 * @param customerDto {@link CustomerDTO}
	 * @throws BusinessException {@link BusinessException}
	 */
	@POST
	public Response registerCustomer(CustomerDTO customerDto) throws BusinessException{
		RestPreconditions.checkNotNul(customerDto);
		customerService.create(customerDto);
		writeLogAPIV1(EnumAPI.CREATE_CUSTOMER.getKey());
		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

}
