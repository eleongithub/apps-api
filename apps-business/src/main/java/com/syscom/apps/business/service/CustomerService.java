package com.syscom.apps.business.service;

import com.syscom.apps.dto.CustomerDTO;
import com.syscom.apps.exception.BusinessException;

/**
 * Contrat d'interface du service des utilisateurs
 * 
 * @author Eric LEGBA
 */
public interface CustomerService {
	
	/**
	 * Enregistrer un nouvel utilisateur
	 * 
	 * @param customerDTO DTO des données de l'utilisateur {@link CustomerDTO}
	 * @throws BusinessException Exception fonctionnelle {@link BusinessException}
	 * @author Eric LEGBA
	 */
	void create(CustomerDTO customerDTO) throws BusinessException;
	
	
	/**
	 * 
	 * Modifier les informations d'un utilisateur
	 * 
	 * @param customerDTO DTO des données de l'utilisateur {@link CustomerDTO}
	 * @return customer {@link CustomerDTO}
	 * @throws BusinessException Exception fonctionnelle {@link BusinessException}
	 * @author Eric LEGBA
	 */
	CustomerDTO update(CustomerDTO customerDTO) throws BusinessException;
	
	
	/**
	 * 
	 * Supprimer un utilisateur
	 * 
	 * @param id Identifiant de l'utilisateur à supprimer {@link Long} 
	 * @author Eric LEGBA
	 */
	void delete(Long id);
	

}
