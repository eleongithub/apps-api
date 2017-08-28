package com.syscom.apps.business.service;

import com.syscom.apps.dto.TokenDTO;
import com.syscom.apps.exception.BusinessException;

/**
 * Contrat d'interface du service d'authentification 
 * 
 * @author Eric LEGBA
 */
public interface LoginService {
	
	/**
	 * Authenttification d'un utilisateur en vue d'obtenir un token valide.
	 * 
	 * @param authorization hash du login:Mot de passe {@link String}
	 * @return TokenDTO d'authentification {@link TokenDTO}
	 * @throws BusinessException : Exception fonctionnelle {@link BusinessException}
	 */
	TokenDTO authenticateCustomer(String authorization) throws BusinessException;

}
