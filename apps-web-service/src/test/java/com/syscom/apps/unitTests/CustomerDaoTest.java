package com.syscom.apps.unitTests;

import static com.syscom.apps.enums.EnumRole.ROLE_CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.syscom.apps.AbstractTest;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.dao.CustomerDao;
import com.syscom.apps.dao.RoleDao;
import com.syscom.apps.enums.EnumRole;
import com.syscom.apps.model.Customer;

public class CustomerDaoTest extends AbstractTest {
	private static final String PHONE = "PHONE";
	private static final String NAME = "NAME";
	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String MAIL = "MAIL";
	private static final String PASSWORD = "PASSWORD";
	private static final String SALT = "SALT";

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private RoleDao roleDao;

	@Before
	public void beforeTest() {
		Customer customer = new Customer();
		customer.setFirstName(FIRST_NAME);
		customer.setMail(MAIL);
		customer.setName(NAME);
		customer.setPassword(PASSWORD);
		customer.setSalt(SALT);
		customer.setPhone(PHONE);
		customer.setRole(roleDao.findRoleByCode(ROLE_CUSTOMER.name()));
		customerDao.create(customer);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void createEmptyCustomer() {
		Customer customer = new Customer();
		customerDao.create(customer);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void createCustomerWithoutName() {
		Customer customer = new Customer();
		customer.setFirstName("first name");
		customer.setMail("mail@syscom.com");
		customer.setPassword("mypassword");
		customer.setSalt("salt");
		customer.setPhone("0679854521");
		customer.setRole(roleDao.findRoleByCode(ROLE_CUSTOMER.name()));
		customerDao.create(customer);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void createCustomerWithoutFirstName() {
		Customer customer = new Customer();
		customer.setName("name");
		customer.setMail("mail@syscom.com");
		customer.setPassword("mypassword");
		customer.setSalt("salt");
		customer.setPhone("0679854521");
		customer.setRole(roleDao.findRoleByCode(ROLE_CUSTOMER.name()));
		customerDao.create(customer);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void createCustomerWithoutRole() {
		Customer customer = new Customer();
		customer.setName("name");
		customer.setFirstName("first name");
		customer.setMail("mail@syscom.com");
		customer.setPassword("mypassword");
		customer.setSalt("salt");
		customer.setPhone("0679854521");
		customerDao.create(customer);

	}
	
	@Test
	public void findCustomer() {
		CustomerCriteria customerCriteria = new CustomerCriteria();
		List<Customer> customers = customerDao.findCustomersByCriteria(customerCriteria);
		Customer customer = customers.get(0);
		assertThat(customer.getMail()).isEqualTo(MAIL);
		assertThat(customer.getName()).isEqualTo(NAME);
		assertThat(customer.getPassword()).isEqualTo(PASSWORD);
		assertThat(customer.getSalt()).isEqualTo(SALT);
		assertThat(customer.getPhone()).isEqualTo(PHONE);
		assertThat(customer.getRole()).isNotNull();
		assertThat(customer.getRole().getCode()).isEqualTo(EnumRole.ROLE_CUSTOMER.name());
		assertThat(customer.getRole().getId()).isNotNull();
		assertThat(customer.getRole().getCreateDate()).isNotNull();
		assertThat(customer.getRole().getUpdateDate()).isNotNull();
	}
	
	@Test
	public void existsCustomerByMail() {
		assertThat(customerDao.existsCustomerByMail(MAIL)).isTrue();
		assertThat(customerDao.existsCustomerByMail("unknownMail")).isFalse();
	}
	
	@Test
	public void existsCustomerByPhone() {
		assertThat(customerDao.existsCustomerByPhone(PHONE)).isTrue();
		assertThat(customerDao.existsCustomerByPhone("unknownPhone")).isFalse();
	}
	
	@Test
	public void existsCustomerByNameFirstName() {
		assertThat(customerDao.existsCustomerByNameFirstName(NAME, FIRST_NAME)).isTrue();
		assertThat(customerDao.existsCustomerByNameFirstName("unknownName", FIRST_NAME)).isFalse();
		assertThat(customerDao.existsCustomerByNameFirstName(NAME, "unknownFirstName")).isFalse();
		assertThat(customerDao.existsCustomerByNameFirstName("unknownName", "unknownFirstName")).isFalse();
	}
	
	@Test
	public void updateCustomer() {
		CustomerCriteria customerCriteria = new CustomerCriteria();
		List<Customer> customers = customerDao.findCustomersByCriteria(customerCriteria);
		Customer customer = customers.get(0);
		String newMail = "_mail@syscom.com";
		String newPhone = "_phone";
		customer.setMail(newMail);
		customer.setPhone(newPhone);
		customerDao.update(customer);
		Customer updateCustomer = customerDao.findCustomerByMail(customer.getMail());
		assertThat(updateCustomer.getMail()).isEqualTo(newMail);
		assertThat(updateCustomer.getPhone()).isEqualTo(newPhone);
	}

	@Test
	public void delete() {
		CustomerCriteria customerCriteria = new CustomerCriteria();
		List<Customer> customers = customerDao.findCustomersByCriteria(customerCriteria);
		Customer customer = customers.get(0);
		customerDao.delete(customer.getId());
		customers = customerDao.findCustomersByCriteria(customerCriteria);
		assertThat(customers).isEmpty();
	}

}
