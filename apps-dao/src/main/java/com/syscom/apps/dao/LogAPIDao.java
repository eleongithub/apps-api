package com.syscom.apps.dao;

import org.apache.ibatis.annotations.Param;

import com.syscom.apps.model.LogAPI;

/**
 * DAO pour gérer la persistence des logs API en BDD
 * 
 * @author Eric LEGBA
 *
 */
public interface LogAPIDao {

	/**
	 * 
	 * Stocker en BDD un nouvel log d'API
	 * 
	 * @param customer {@link LogAPI}
	 * @author Eric LEGBA
	 */
	void create(@Param("logAPI")LogAPI logAPI);
	
	/**
	 * 
	 * Rechercher un log d'API à partir de son code
	 * 
	 * @param codeAPI {@link String}
	 * @return LogAPI {@link LogAPI}
	 * @author Eric LEGBA
	 */
	LogAPI findByCode(@Param("codeAPI")String codeAPI);
	
}
