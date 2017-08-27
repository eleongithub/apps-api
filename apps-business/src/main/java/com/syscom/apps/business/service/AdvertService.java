package com.syscom.apps.business.service;

import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.model.Advert;

/**
 * Contrat d'interface du service pour gérer les annonces
 * 
 * @author Eric LEGBA
 */
public interface AdvertService {

	/**
	 * Enregistrer une nouvelle annonce
	 * 
	 * @param advert annonce à créer {@link Advert}
	 * @throws BusinessException Exception fonctionnelle {@link BusinessException}
	 */
	void create(Advert advert) throws BusinessException;
	
}
