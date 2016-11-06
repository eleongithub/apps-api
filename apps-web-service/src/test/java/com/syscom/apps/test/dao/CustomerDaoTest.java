package com.syscom.apps.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.syscom.apps.dao.CustomerDao;
import com.syscom.apps.model.Customer;
import com.syscom.apps.test.AbstractTest;

public class CustomerDaoTest extends AbstractTest{
	private static final String LOGIN = "LOGIN";
	private static final String PHONE = "PHONE";
	private static final String NAME = "NAME";
	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String MAIL = "MAIL";
	private static final String PASSWORD = "PASSWORD";
	private static final String SALT = "SALT";
	
	@Autowired
	private CustomerDao customerDao;
	
	@Test
	public void insertCustomer(){
		Customer customer = new Customer();
		customer.setLogin(LOGIN);
		customer.setFirstName(FIRST_NAME);
		customer.setMail(MAIL);
		customer.setName(NAME);
		customer.setPassword(PASSWORD);
		customer.setSalt(SALT);
		customer.setPhone(PHONE);
		customerDao.create(customer);
	}

}
