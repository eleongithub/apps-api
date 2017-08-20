package com.syscom.apps.web.exceptions.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import com.syscom.apps.exception.TechnicalException;

public class TechnicalExceptionMapper implements ExceptionMapper<TechnicalException>{

	@Override
	public Response toResponse(TechnicalException technicalException) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON_TYPE).entity(technicalException.getMessage()).build();
	}
	
}
