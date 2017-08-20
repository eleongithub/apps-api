package com.syscom.apps.unitTests;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import com.syscom.apps.AbstractTest;
import com.syscom.apps.dao.LogAPIDao;
import com.syscom.apps.model.LogAPI;
import com.syscom.apps.web.enums.EnumAPI;
import com.syscom.apps.web.enums.EnumVersion;

public class LogAPIDaoTest extends AbstractTest{
	
	@Autowired
	private LogAPIDao logAPIDao;
	
	@Test
	public void insertLog(){
	 LogAPI logAPI = new LogAPI();
	 logAPI.setCodeAPI(EnumAPI.CREATE_CUSTOMER.getKey());
	 logAPI.setVersion(EnumVersion.V1.name());
	 logAPI.setNameAPI(EnumAPI.CREATE_CUSTOMER.name());
	 logAPIDao.create(logAPI);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void insertLogWithoutVersion(){
		 LogAPI logAPI = new LogAPI();
		 logAPI.setCodeAPI(EnumAPI.CREATE_CUSTOMER.getKey());
		 logAPI.setNameAPI(EnumAPI.CREATE_CUSTOMER.name());
		 logAPIDao.create(logAPI);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void insertLogWithoutCodeAPI(){
		 LogAPI logAPI = new LogAPI();
		 logAPI.setVersion(EnumVersion.V1.name());
		 logAPI.setNameAPI(EnumAPI.CREATE_CUSTOMER.name());
		 logAPIDao.create(logAPI);
	}

	@Test
	public void fingLogAPIByCode(){
		LogAPI logAPI = new LogAPI();
		logAPI.setCodeAPI(EnumAPI.CREATE_CUSTOMER.getKey());
		logAPI.setVersion(EnumVersion.V1.name());
		logAPI.setNameAPI(EnumAPI.CREATE_CUSTOMER.name());
		logAPIDao.create(logAPI);
		LogAPI log = logAPIDao.findByCode(EnumAPI.CREATE_CUSTOMER.getKey());
		Assertions.assertThat(log).isNotNull();
		Assertions.assertThat(log.getCodeAPI()).isEqualTo(EnumAPI.CREATE_CUSTOMER.getKey());
		Assertions.assertThat(log.getVersion()).isEqualTo(EnumVersion.V1.name());
		Assertions.assertThat(log.getCreateDate()).isNotNull();
		Assertions.assertThat(log.getUpdateDate()).isNotNull();
	}
	
}
