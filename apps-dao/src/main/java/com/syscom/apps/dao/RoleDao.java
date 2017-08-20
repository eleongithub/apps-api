package com.syscom.apps.dao;

import org.apache.ibatis.annotations.Param;

import com.syscom.apps.model.referential.Role;

/**
 * DAO pour gérer la persistence des rôles en BDD
 * 
 * @author Eric LEGBA
 *
 */
public interface RoleDao {

	/**
	 * 
	 * Rechercher un rôle à partir d'un code
	 * 
	 * @param code {@link String}
	 * @return role {@link Role}
	 * @author Eric LEGBA
	 */
	Role findRoleByCode(@Param("code")String code);
}
