package com.syscom.apps.business.service;

import com.syscom.apps.dto.TokenDTO;
import com.syscom.apps.exception.BusinessException;

/**
 * Service d'authentification 
 * 
 * 
 * @author Eric LEGBA
 *
 */
public interface LoginService {
	
	/**
	 * Authenttification d'un utilisateur pour obtenir un token valide.
	 * 
	 * @param authorization {@link String}
	 * @return {@link TokenDTO}
	 * @throws BusinessException {@link BusinessException}
	 * @author Eric LEGBA
	 */
	TokenDTO authenticateCustomer(String authorization) throws BusinessException;

}
