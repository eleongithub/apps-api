package com.syscom.apps.web.exceptions.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.syscom.apps.exception.BusinessException;

public class BusinessExceptionMapper implements ExceptionMapper<BusinessException>{

	@Override
	public Response toResponse(BusinessException businessException) {
		String message = businessException.getMessage();
		int statusCode = businessException.getStatusCode();
		Response.Status status;
		if(statusCode == 0 ){
			status = Response.Status.BAD_REQUEST;
		}else{
			status = Response.Status.fromStatusCode(statusCode);
			if(status==null){
				status = Response.Status.BAD_REQUEST;
			}
		}
		return Response.status(status).type(MediaType.APPLICATION_JSON_TYPE).entity(message).build();
	}
	
}
