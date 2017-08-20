package com.syscom.apps.business.service;

import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.model.Advert;

/**
 * Service pour gérer les annonces
 * 
 * 
 * @author Eric LEGBA
 *
 */
public interface AdvertService {

	/**
	 * Enregistrer une nouvelle annonce
	 * 
	 * @param advert {@link Advert}
	 * @throws BusinessException {@link BusinessException}
	 * @author Eric LEGBA
	 */
	void create(Advert advert) throws BusinessException;
	
}
