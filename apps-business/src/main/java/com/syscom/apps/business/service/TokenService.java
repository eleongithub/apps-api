package com.syscom.apps.business.service;

import com.syscom.apps.dto.TokenDTO;
import com.syscom.apps.model.Token;

/**
 * Contrat d'interface du service des tokens d'authentification
 * 
 * @author Eric LEGBA
 *
 */
public interface TokenService {
	
	/**
	 * Recherche d'un token 
	 *
	 * @param value
	 * @return {@link Token}
	 */
	Token findValidToken(String value);
	
	/**
	 * Recherche d'un token 
	 *
	 * @param value
	 * @return {@link Token}
	 */
	TokenDTO findToken(String value);

}
