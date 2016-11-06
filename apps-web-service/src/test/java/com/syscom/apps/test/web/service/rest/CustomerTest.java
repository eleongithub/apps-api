package com.syscom.apps.test.web.service.rest;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.test.web.service.AbstractWebServiceTest;
import com.syscom.apps.web.service.models.CustomerWS;

public class CustomerTest extends AbstractWebServiceTest {

	private static final String LOGIN = "LOGIN";
	private static final String PHONE = "PHONE";
	private static final String NAME = "NAME";
	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String MAIL = "MAIL";
	private static final String PASSWORD = "PASSWORD";
	private static final String SALT = "SALT";

	@Before
	public void beforTest() throws Exception {
		CustomerWS customer = new CustomerWS();
		customer.setFirstName(FIRST_NAME);
		customer.setLogin(LOGIN);
		customer.setMail(MAIL);
		customer.setName(NAME);
		customer.setPhone(PHONE);
		postRequest("customer", convertFromObjectToJson(customer));
	}

	@Test
	public void testCreateCustomerWithSameLogin() throws Exception {
		String api = "customer";
		CustomerWS customer = new CustomerWS();
		customer.setFirstName("firstName");
		customer.setLogin("toto");
		customer.setMail("mail");
		customer.setName("name");
		customer.setPhone("telephone");
		postRequest(api, convertFromObjectToJson(customer));
	}

}
