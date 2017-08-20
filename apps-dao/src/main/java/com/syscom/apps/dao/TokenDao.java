package com.syscom.apps.dao;

import org.apache.ibatis.annotations.Param;

import com.syscom.apps.model.Token;

/**
 * DAO pour gérer la persistence des tokens
 * 
 * @author Eric LEGBA
 *
 */
public interface TokenDao {
	
	
	/**
	 * 
	 * Créer un token
	 * 
	 * @param token {@link Token}
	 * @author Eric LEGBA
	 */
	void create(@Param("token")Token token);
	
	/**
	 * 
	 * Suppression des tokens expirés
	 * @author Eric LEGBA
	 */
	void deleteExpiredTokens();
	
	
	/**
	 * 
	 * Recherche un token à partir de l'identifiant d'un utilisateur
	 * 
	 * @param customerId {@link Long}
	 * @return Token {@link Token}
	 * @author Eric LEGBA
	 */
	Token findValidTokenByCustomerId(@Param("customerId")Long customerId);
	
	
	/**
	 * 
	 * Rechercher un token en cours de validité à partir d'un accessID (GUID value)
	 * 
	 * @param accessToken {@link String}
	 * @return Token {@link Token}
	 * @author Eric LEGBA
	 */
	Token findValidTokenByAccessToken(@Param("accessToken")String accessToken);

}
