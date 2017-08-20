package com.syscom.apps.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.syscom.apps.business.service.LogAPIService;
import com.syscom.apps.model.LogAPI;
import com.syscom.apps.web.enums.EnumVersion;

//abstract
public  class BaseEndPoint {
	
	@Autowired
	private LogAPIService logAPIService;
	
	protected void writeLogAPIV1(String codeAPI){
		this.writeLogAPI(codeAPI, EnumVersion.V1.name());
	}
	
	protected void writeLogAPI(String codeAPI, String version){
		LogAPI logAPI = new LogAPI(codeAPI,version);
		logAPIService.create(logAPI);
	}

}
