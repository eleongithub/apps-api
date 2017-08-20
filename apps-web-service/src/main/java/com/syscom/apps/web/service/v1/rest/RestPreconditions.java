package com.syscom.apps.web.service.v1.rest;

import com.syscom.apps.exception.BusinessException;

/**
 * 
 * @author EL1638EN
 *
 */
public class RestPreconditions {

	private RestPreconditions(){
	    super();
	}
	
	public static <T> T checkNotNul(final T resource) throws BusinessException {
        if (resource == null) {
            throw new BusinessException("Reject null object !");
        }
        return resource;
    }
	
}
