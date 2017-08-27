package com.syscom.apps.dao;

import org.apache.ibatis.annotations.Param;
import com.syscom.apps.model.Token;

/**
 * Contrat d'interface du DAO pour gérer la persistence des tokens
 * 
 * @author Eric LEGBA
 *
 */
public interface TokenDao {
	
	
	/**
	 * Créer un token
	 * @param token {@link Token}
	 */
	void create(@Param("token")Token token);
	
	
	/**
	 * Suppression des tokens expirés
	 */
	void deleteExpiredTokens();
	
	
	/**
	 * 
	 * Recherche un token à partir de l'identifiant d'un utilisateur
	 * 
	 * @param customerId {@link Long}
	 * @return Token {@link Token}
	 */
	Token findValidTokenByCustomerId(@Param("customerId")Long customerId);
	
	
	/**
	 * 
	 * Rechercher un token en cours de validité à partir d'un accessID (GUID value)
	 * 
	 * @param value {@link String}
	 * @return Token {@link Token}
	 */
	Token findValidTokenByValue(@Param("value")String value);

}
