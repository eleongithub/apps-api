package com.syscom.apps.web.service.v1.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.syscom.apps.business.service.AdvertService;
import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.model.Advert;
import com.syscom.apps.web.enums.EnumAPI;
import com.syscom.apps.web.service.BaseEndPoint;
import com.syscom.apps.web.utils.Functions;

@Component("advertEndPoint")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("/v1/advert")
public class AdvertEndPoint extends BaseEndPoint {
	
	@Autowired
	private AdvertService advertService;
	
	/**
	 * 
	 * Create advert
	 * 
	 * @param advert {@link Advert}
	 * @throws BusinessException {@link BusinessException}
	 */
	@POST
	@Secured(Functions.CREATE_ADVERT)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response createAdvert(Advert advert) throws BusinessException{
		RestPreconditions.checkNotNul(advert);
		advertService.create(advert);
		writeLogAPIV1(EnumAPI.CREATE_ADVERT.getKey());
		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

}
