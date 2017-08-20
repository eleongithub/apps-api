package com.syscom.apps.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syscom.apps.business.service.TokenService;
import com.syscom.apps.dao.TokenDao;
import com.syscom.apps.model.Token;

/** {@inheritDoc} */
@Service
@Transactional(rollbackFor=Exception.class)
public class TokenServiceImpl implements TokenService{

	@Autowired
	private TokenDao tokenDao;
	
	/** {@inheritDoc} */
	@Override
	public Token findValidToken(String accessToken) {
		tokenDao.deleteExpiredTokens();
		return tokenDao.findValidTokenByAccessToken(accessToken);
	}

}
