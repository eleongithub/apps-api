package com.syscom.apps.business.service.impl;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class BaseService {
	
	@Autowired
	private MessageSource messageSource;
	

	protected String getMessage(String key){
		return this.getMessage(key, null, new Object[]{});
	}
	
	protected String getMessage(String key, Object... args){
		return this.getMessage(key, null, args);
	}

	protected String getMessage(String key, Locale locale){
		return this.getMessage(key,locale, new Object[]{});
	}
	
	protected String getMessage(String key, Locale locale,  Object... args){
		return messageSource.getMessage(key,args,locale);
	}

}
