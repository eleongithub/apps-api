package com.syscom.apps.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.syscom.apps.business.service.LogAPIService;
import com.syscom.apps.dao.LogAPIDao;
import com.syscom.apps.model.LogAPI;

/** {@inheritDoc} */
@Service("logAPIService")
@Transactional(rollbackFor = Exception.class)
public class LogAPIServiceImpl implements LogAPIService{

	@Autowired
	private LogAPIDao logAPIDao;
	
	/** {@inheritDoc} */
	@Override
	public void create(LogAPI logAPI) {
		logAPIDao.create(logAPI);
	}

	/** {@inheritDoc} */
	@Override
	public LogAPI findByCode(String codeAPI) {
		return logAPIDao.findByCode(codeAPI);
	}

}
