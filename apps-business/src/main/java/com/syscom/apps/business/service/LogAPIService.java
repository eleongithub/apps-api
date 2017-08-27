package com.syscom.apps.business.service;

import com.syscom.apps.model.LogAPI;

/**
 * Contrat d'interface du service des logs des APIs
 * 
 * @author Eric LEGBA
 */
public interface LogAPIService {
	
	/**
	 * 
	 * Enregistrer un log d'API
	 * 
	 * @param logAPI les informations de l'API à enregistrer {@link LogAPI}
	 * @author Eric LEGBA
	 */
	void create(LogAPI logAPI);
	
	/**
	 * 
	 * Recherche un Log d'API à partir d'un code
	 * 
	 * @param codeAPI code de l'API {@link String}
	 * @return LogAPI log API correspondant {@link LogAPI}
	 * @author Eric LEGBA
	 */
	LogAPI findByCode(String codeAPI);

}
