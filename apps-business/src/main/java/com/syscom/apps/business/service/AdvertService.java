package com.syscom.apps.business.service;

import com.syscom.apps.dto.AdvertDTO;
import com.syscom.apps.exception.BusinessException;

/**
 * Contrat d'interface du service pour gérer les annonces
 * 
 * @author Eric LEGBA
 */
public interface AdvertService {
	
	/**
	 * Enregistrer une nouvelle annonce
	 * 
	 * @param advertDTO : annonce à créer {@link AdvertDTO}
	 * @throws BusinessException : Exception fonctionnelle {@link BusinessException}
	 */
	void create(AdvertDTO advertDTO) throws BusinessException;
}
