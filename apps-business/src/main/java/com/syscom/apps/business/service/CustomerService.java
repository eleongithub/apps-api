package com.syscom.apps.business.service;

import com.syscom.apps.dto.CustomerDTO;
import com.syscom.apps.exception.BusinessException;

/**
 * Service des utilisateurs
 * 
 * 
 * @author Eric LEGBA
 *
 */
public interface CustomerService {
	
	/**
	 * 
	 * Enregistrer un nouvel utilisateur
	 * 
	 * @param customerDTO {@link CustomerDTO}
	 * @throws BusinessException {@link BusinessException}
	 * @author Eric LEGBA
	 */
	void create(CustomerDTO customerDTO) throws BusinessException;
	
	
	/**
	 * 
	 * Modifier les informations d'un utilisateur
	 * 
	 * @param customerDTO {@link CustomerDTO}
	 * @return customer {@link CustomerDTO}
	 * @throws BusinessException {@link BusinessException}
	 * @author Eric LEGBA
	 */
	CustomerDTO update(CustomerDTO customerDTO) throws BusinessException;
	
	
	/**
	 * 
	 * Supprimer un utilisateur
	 * 
	 * @param id {@link Long} 
	 * @author Eric LEGBA
	 */
	void delete(Long id);
	

}
