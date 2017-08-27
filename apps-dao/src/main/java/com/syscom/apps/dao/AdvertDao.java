package com.syscom.apps.dao;

import org.apache.ibatis.annotations.Param;
import com.syscom.apps.model.Advert;

/**
 * DAO pour gérer la persistence des annonces
 * 
 * @author Eric LEGBA
 */
public interface AdvertDao {
	
	/**
	 * Créer une nouvelle annonce
	 * 
	 * @param advert {@link Advert}
	 * @author Eric LEGBA
	 */
	void create(@Param("advert")Advert advert);

}
