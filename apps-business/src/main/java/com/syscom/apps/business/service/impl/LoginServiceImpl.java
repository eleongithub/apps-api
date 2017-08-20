package com.syscom.apps.business.service.impl;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.base.Joiner;
import com.syscom.apps.business.service.LoginService;
import com.syscom.apps.business.service.utils.Encryption;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.dao.CustomerDao;
import com.syscom.apps.dao.TokenDao;
import com.syscom.apps.dto.CustomerDTO;
import com.syscom.apps.dto.TokenDTO;
import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.exception.TechnicalException;
import com.syscom.apps.model.Customer;
import com.syscom.apps.model.Token;

/** {@inheritDoc} */
@Service("loginService")
@Transactional(rollbackFor=Exception.class)
public class LoginServiceImpl extends BaseService implements LoginService {

	private static final int ONE_HOUR_IN_MINUTE = 60;

	@Autowired
	private TokenDao tokenDao;

	@Autowired
	private CustomerDao customerDao;

	
	/** {@inheritDoc} */
	@Override
	public TokenDTO authenticateCustomer(String authorization) throws BusinessException {
		
		Assert.notNull(authorization,"Le credential doit être non nul");

		String credentials = new String(Base64.getDecoder().decode(authorization.getBytes()));
//		Split login and password tokens
		String[] tokenizer = StringUtils.split(credentials, ":");
		if(tokenizer==null || tokenizer.length!=2){
			throw new BusinessException("Le credential est invalide");
		}
        String mail = tokenizer[0];
        String password = tokenizer[1];
		List<String> appsErrors = new ArrayList<>();
		// Check mail
		if (isEmpty(mail)) {
			appsErrors.add(getMessage("create.customer.mail"));
		}
		// check password
		if (isEmpty(password)) {
			appsErrors.add(getMessage("create.customer.password"));
		}
		if (!appsErrors.isEmpty()) {
			String message = getMessage("login.customer.empty.error")
					+ Joiner.on(",").join(appsErrors);
			throw new BusinessException(message);
		}

		Token token=null;
		// 1 - find user by login and password
		CustomerCriteria criteria = new CustomerCriteria();
		criteria.setMail(mail);
		List<Customer> customers = customerDao.findCustomersByCriteria(criteria);
		if (customers.isEmpty()) {
			throw new BusinessException(getMessage("login.unknown.customer.error"));
		} 
		Customer customer = customers.get(0);
		String passwordDB = customer.getPassword();
		String salt = customer.getSalt();
		try{
			String passwordEncrypt = Encryption.getInstance().decrypt(password, salt);
			if (!StringUtils.equals(passwordDB, passwordEncrypt)) {
				throw new BusinessException(getMessage("login.unknown.customer.error"));
			} 
			// 2 - delete expired tokens before find user's token
			tokenDao.deleteExpiredTokens();
			// 3 - find valid token for user
			token = tokenDao.findValidTokenByCustomerId(customer.getId());
			// 4 - If valid token doesn't exist, create new token and save into the data base
			if (token == null) {
				token = new Token();
				token.setAccessToken(UUID.randomUUID().toString());
				token.setCustomer(customer);
				Calendar now = Calendar.getInstance();
				now.add(Calendar.MINUTE, ONE_HOUR_IN_MINUTE);
				token.setExpirationDate(now.getTime());
				tokenDao.create(token);
			}
			
		}catch (NoSuchAlgorithmException algorithmException){
			throw new TechnicalException(algorithmException);
		}
		return convertToDTO(token);
	}

	/**
	 * Conversion d'un objet de token vers le Dto
	 * @param token
	 * @return {@link TokenDTO}
	 * @author Eric LEGBA
	 */
	private TokenDTO convertToDTO(Token token) {
		if(token==null){
			return null;
		}
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setAccessToken(token.getAccessToken());
		tokenDTO.setExpiration(token.getExpirationDate());
		tokenDTO.setCustomerDTO(new CustomerDTO(token.getCustomer()));
		return tokenDTO;
	}

}
