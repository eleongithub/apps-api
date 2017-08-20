package com.syscom.apps.business.service;

import com.syscom.apps.model.Token;

/**
 * Service des tokens d'authentification
 * 
 * @author Eric LEGBA
 *
 */
public interface TokenService {
	
	/**
	 *Recherche d'un token 
	 *
	 * @param accessToken
	 * @return {@link Token}
	 * @author Eric LEGBA
	 */
	Token findValidToken(String accessToken);

}
