package com.syscom.apps.unitTests;

import static com.syscom.apps.enums.EnumRole.ROLE_CUSTOMER;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import com.syscom.apps.AbstractTest;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.dao.CustomerDao;
import com.syscom.apps.dao.RoleDao;
import com.syscom.apps.dao.TokenDao;
import com.syscom.apps.model.Customer;
import com.syscom.apps.model.Token;

public class TokenDaoTest extends AbstractTest{

	@Autowired
	private TokenDao tokenDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private RoleDao roleDao;
	
	private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
	
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
	public void createTokenWithoutAccessToken(){
		createCustomer();
		List<Customer> customers = customerDao.findCustomersByCriteria(new CustomerCriteria());
		Customer customer = customers.get(0);
		Token token = new Token();
		token.setCustomer(customer);
		token.setExpiration(new Date());
		tokenDao.create(token);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void createTokenWithoutCustomer(){
		Token token = new Token();
		token.setExpiration(new Date());
		token.setValue(ACCESS_TOKEN);
		tokenDao.create(token);
	}

	@Test
	public void createToken(){
		createCustomer();
		List<Customer> customers = customerDao.findCustomersByCriteria(new CustomerCriteria());
		Customer customer = customers.get(0);
		Token token = new Token();
		token.setCustomer(customer);
		token.setExpiration(new Date());
		token.setValue(ACCESS_TOKEN);
		tokenDao.create(token);
	}
	
	@Test
	public void deleteExpiredToken(){
		createCustomer();
		List<Customer> customers = customerDao.findCustomersByCriteria(new CustomerCriteria());
		Customer customer = customers.get(0);
		Token token = new Token();
		token.setCustomer(customer);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, -5);
		token.setExpiration(now.getTime());
		token.setValue(ACCESS_TOKEN);
		tokenDao.create(token);
		tokenDao.deleteExpiredTokens();
		Token findToken = tokenDao.findValidTokenByValue(ACCESS_TOKEN);
		assertThat(findToken).isNull();
	}
	
	
	@Test
	public void findValidTokenByCustomerId(){
		createCustomer();
		List<Customer> customers = customerDao.findCustomersByCriteria(new CustomerCriteria());
		Customer customer = customers.get(0);
		Token token = new Token();
		token.setCustomer(customer);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 5);
		token.setExpiration(now.getTime());
		token.setValue(ACCESS_TOKEN);
		tokenDao.create(token);
		Token findToken = tokenDao.findValidTokenByCustomerId(customer.getId());
		assertThat(findToken).isNotNull();
		assertThat(findToken.getValue()).isEqualTo(ACCESS_TOKEN);
		assertThat(findToken.getCustomer()).isNotNull();
		assertThat(findToken.getCustomer().getId()).isEqualTo(customer.getId());
	}
	
}
