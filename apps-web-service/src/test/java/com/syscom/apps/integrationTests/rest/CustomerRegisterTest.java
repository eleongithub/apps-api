package com.syscom.apps.integrationTests.rest;

import java.net.HttpURLConnection;
import javax.ws.rs.core.Response;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import com.syscom.apps.dto.CustomerDTO;
import com.syscom.apps.integrationTests.AbstractWebServiceTest;

public class CustomerRegisterTest extends AbstractWebServiceTest {
	
//	TODO à regarder !! http://jenkins-le-guide-complet.github.io/html/appendix-automating-your-tests.html

	private static final String PHONE = "PHONE";
	private static final String NAME = "NAME";
	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String MAIL = "MAIL";
	private static final String PASSWORD = "PASSWORD";


	@Before
	public void beforTest() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(FIRST_NAME);
		customerDTO.setMail(MAIL);
		customerDTO.setName(NAME);
		customerDTO.setPhone(PHONE);
		customerDTO.setPassword(PASSWORD);
		postRequest("/customerRegister", convertFromObjectToJson(customerDTO));
	}
	
	@Test
	public void createNullCustomer() throws Exception {
		HttpURLConnection connection = postRequest("/customerRegister", convertFromObjectToJson(null));
		Assertions.assertThat(connection.getResponseCode()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
	}
	
	@Test
	public void createCustomerWithExistNameAndFirstname() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(FIRST_NAME);
		customerDTO.setName(NAME);
		customerDTO.setMail("mail1");
		customerDTO.setPhone("phone1");
		customerDTO.setPassword("password1");
		HttpURLConnection connection = postRequest("/customerRegister", convertFromObjectToJson(customerDTO));
		Assertions.assertThat(connection.getResponseCode()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
	}
	
	
	@Test
	public void createCustomerWithExistMail() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("first name1");
		customerDTO.setName("name1");
		customerDTO.setMail(MAIL);
		customerDTO.setPhone("phone1");
		customerDTO.setPassword("password1");
		HttpURLConnection connection = postRequest("/customerRegister", convertFromObjectToJson(customerDTO));
		Assertions.assertThat(connection.getResponseCode()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
	}
	
	@Test
	public void createCustomerWithExistPhone() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("first name1");
		customerDTO.setName("name1");
		customerDTO.setMail("mail1");
		customerDTO.setPhone(PHONE);
		customerDTO.setPassword("password1");
		HttpURLConnection connection = postRequest("/customerRegister", convertFromObjectToJson(customerDTO));
		Assertions.assertThat(connection.getResponseCode()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
	}
	
}
