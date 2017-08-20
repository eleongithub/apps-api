package com.syscom.apps.business.service;

import com.syscom.apps.model.LogAPI;

/**
 * Service dédié aux logs des APIs
 * 
 * 
 * @author Eric LEGBA
 *
 */
public interface LogAPIService {
	
	/**
	 * 
	 * Insert Log into the Database
	 * 
	 * @param logAPI {@link LogAPI}
	 * @author Eric LEGBA
	 */
	void create(LogAPI logAPI);
	
	/**
	 * 
	 * Recherche un Log d'API à partir d'un code
	 * 
	 * @param codeAPI {@link String}
	 * @return LogAPI {@link LogAPI}
	 * @author Eric LEGBA
	 */
	LogAPI findByCode(String codeAPI);

}
