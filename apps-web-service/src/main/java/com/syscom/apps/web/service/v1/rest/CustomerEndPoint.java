package com.syscom.apps.web.service.v1.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import com.syscom.apps.business.service.CustomerService;
import com.syscom.apps.dto.CustomerDTO;
import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.web.enums.EnumAPI;
import com.syscom.apps.web.service.BaseEndPoint;
import com.syscom.apps.web.utils.Functions;


@Component("customerEndPoint")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("/v1/customer")
public class CustomerEndPoint extends BaseEndPoint{

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MessageSource messageSource;
		
	/**
	 * 
	 * Update customer
	 * 
	 * @param customerDTO {@link CustomerDTO}
	 * @return updated customer {@link CustomerDTO}
	 */
	
	@PUT
	@Secured(Functions.UPDATE_CUSTOMER)
	public CustomerDTO update(CustomerDTO customerDTO) throws BusinessException{
		RestPreconditions.checkNotNul(customerDTO);
		CustomerDTO customerDTOResult = customerService.update(customerDTO);
		writeLogAPIV1(EnumAPI.UPDATE_CUSTOMER.getKey());
		return customerDTOResult;
	}
	
	/**
	 * 
	 * Delete customer
	 * 
	 * @param id {@link Long}
	 * @return response {@link Response}
	 */
	@DELETE
	@Path("/{id}")
	@Secured(Functions.DELETE_CUSTOMER)
	public Response delete(@PathParam("id")Long id) throws BusinessException{
		RestPreconditions.checkNotNul(id);
		customerService.delete(id);
		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
		
}
