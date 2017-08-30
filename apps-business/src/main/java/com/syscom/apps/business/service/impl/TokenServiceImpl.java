package com.syscom.apps.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.syscom.apps.business.service.TokenService;
import com.syscom.apps.dao.TokenDao;
import com.syscom.apps.dto.TokenDTO;
import com.syscom.apps.model.Token;

/**
 * Implémentation des services des jetons d'authentification 
 * 
 * @author Eric LEGBA
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TokenServiceImpl extends BaseService implements TokenService{

	@Autowired
	private TokenDao tokenDao;
	
	/** {@inheritDoc} */
	@Override
	public Token findValidToken(String value) {
		Assert.notNull(value,getMessage("token.value.mandatory"));
		tokenDao.deleteExpiredTokens();
		return tokenDao.findValidTokenByValue(value);
	}

	@Override
	public TokenDTO findToken(String value) {
		Assert.notNull(value,getMessage("token.value.mandatory"));
		tokenDao.deleteExpiredTokens();
		Token token = tokenDao.findValidTokenByValue(value);
		return token ==null ? null : new TokenDTO(token);
	}

}
