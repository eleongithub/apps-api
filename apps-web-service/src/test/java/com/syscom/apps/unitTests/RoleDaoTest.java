package com.syscom.apps.unitTests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import com.syscom.apps.AbstractTest;
import com.syscom.apps.dao.RoleDao;
import com.syscom.apps.enums.EnumRole;
import com.syscom.apps.model.referential.Role;

public class RoleDaoTest extends AbstractTest{
	
	@Autowired
	private RoleDao roleDao;
	
	@Test
	public void fintRoleByCode(){
		Role role = roleDao.findRoleByCode(EnumRole.ROLE_CUSTOMER.name());
		assertThat(role).isNotNull();
		assertThat(role.getCode()).isEqualTo(EnumRole.ROLE_CUSTOMER.name());
	}

}
