package com.syscom.apps.unitTests;

import static com.syscom.apps.enums.EnumRole.ROLE_CUSTOMER;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import com.syscom.apps.AbstractTest;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.dao.AdvertDao;
import com.syscom.apps.dao.CustomerDao;
import com.syscom.apps.dao.RoleDao;
import com.syscom.apps.model.Advert;
import com.syscom.apps.model.Customer;

public class AdvertDaoTest extends AbstractTest{

	@Autowired
	private AdvertDao advertDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private RoleDao roleDao;
	
	private void createCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("first_name");
		customer.setMail("mail");
		customer.setName("name");
		customer.setPassword("password");
		customer.setSalt("salt");
		customer.setPhone("phone");
		customer.setRole(roleDao.findRoleByCode(ROLE_CUSTOMER.name()));
		customerDao.create(customer);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void createAdvertWithoutTitle(){
		createCustomer();
		CustomerCriteria customerCriteria = new CustomerCriteria();
		List<Customer> customers = customerDao.findCustomersByCriteria(customerCriteria);
		Customer customer = customers.get(0);
		Advert advert = new Advert();
		advert.setCustomer(customer);
		advert.setDescription("description");
		advert.setPrice(1.0F);
		advertDao.create(advert);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void createAdvertWithoutDescription(){
		createCustomer();
		CustomerCriteria customerCriteria = new CustomerCriteria();
		List<Customer> customers = customerDao.findCustomersByCriteria(customerCriteria);
		Customer customer = customers.get(0);
		Advert advert = new Advert();
		advert.setCustomer(customer);
		advert.setTitle("title");
		advert.setPrice(1.0F);
		advertDao.create(advert);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void createAdvertWithoutPrice(){
		createCustomer();
		CustomerCriteria customerCriteria = new CustomerCriteria();
		List<Customer> customers = customerDao.findCustomersByCriteria(customerCriteria);
		Customer customer = customers.get(0);
		Advert advert = new Advert();
		advert.setCustomer(customer);
		advert.setTitle("title");
		advert.setDescription("description");
		advertDao.create(advert);
	}

	@Test
	public void createAdvert(){
		createCustomer();
		CustomerCriteria customerCriteria = new CustomerCriteria();
		List<Customer> customers = customerDao.findCustomersByCriteria(customerCriteria);
		Customer customer = customers.get(0);
		Advert advert = new Advert();
		advert.setCustomer(customer);
		advert.setTitle("title");
		advert.setDescription("description");
		advert.setPrice(1.0F);
		advertDao.create(advert);
	}
	
}
