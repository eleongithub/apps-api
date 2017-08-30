package com.syscom.apps.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.syscom.apps.business.service.LogAPIService;
import com.syscom.apps.dao.LogAPIDao;
import com.syscom.apps.model.LogAPI;

/** {@inheritDoc} */
@Service("logAPIService")
@Transactional(rollbackFor = Exception.class)
public class LogAPIServiceImpl extends BaseService implements LogAPIService{

	@Autowired
	private LogAPIDao logAPIDao;
	
	/** {@inheritDoc} */
	@Override
	public void create(LogAPI logAPI) {
		Assert.notNull(logAPI,getMessage("log.api.mandatory"));
		logAPIDao.create(logAPI);
	}

	/** {@inheritDoc} */
	@Override
	public LogAPI findByCode(String codeAPI) {
		Assert.notNull(codeAPI,getMessage("code.api.mandatory"));
		return logAPIDao.findByCode(codeAPI);
	}

}
