package com.syscom.apps.integrationTests;

import static com.syscom.apps.enums.EnumRole.ROLE_CUSTOMER;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syscom.apps.AbstractTest;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.dao.CustomerDao;
import com.syscom.apps.dao.RoleDao;
import com.syscom.apps.dao.TokenDao;
import com.syscom.apps.model.Customer;
import com.syscom.apps.model.Token;
import com.syscom.apps.web.enums.EnumVersion;

public abstract class AbstractWebServiceTest extends AbstractTest{

//	TODO : Test RestTemplate for Rest Client !!!
	
	protected static EmbeddedServer server;

	private static final String BASE_REST_URI = "WS-REST";
	private static final String BASE_REST_API_URI = "/api";
	private static final String VERSION = EnumVersion.V1.getVersion();
	
	protected static final String CUSTOMER_PHONE = "PHONE";
	protected static final String CUSTOMER_NAME = "NAME";
	protected static final String CUSTOMER_FIRST_NAME = "FIRST_NAME";
	protected static final String CUSTOMER_MAIL = "MAIL";
	protected static final String CUSTOMER_PASSWORD = "PASSWORD";
	protected static final String CUSTOMER_SALT = "SALT";
	protected static final String TOKEN_ACCESS = "ACCESS";
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private TokenDao tokenDao;
	
	
	static {
		server = new EmbeddedServer();
	}

	protected HttpURLConnection get(String uri) throws Exception {
		URL url = server.getUrl(BASE_REST_URI+uri);
		HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
		httpUrlConnection.setRequestMethod("GET");
		return httpUrlConnection;
	}

	protected HttpURLConnection postAPIRequest(String uri,String data) throws Exception {
		String url = BASE_REST_API_URI+"/"+VERSION+uri;
		return this.postRequest(url, data);
	} 
	
	protected HttpURLConnection postRequest(String uri,String data) throws Exception {
		return this.buildHttpURLConnection(uri, "POST", data);
	}
	
	protected HttpURLConnection putRequest(String uri,String data) throws Exception {
		return this.buildHttpURLConnection(uri, "PUT", data);
	}
	
	protected HttpURLConnection deleteRequest(String uri) throws Exception {
		return this.buildHttpURLConnection(uri, "DELETE", null);
	}
	
	private HttpURLConnection buildHttpURLConnection(String uri, String type,String data) throws Exception {
		URL url = server.getUrl(getAbsoluteUrl(uri));
		HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
		httpUrlConnection.setRequestMethod(type);
		httpUrlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		httpUrlConnection.setRequestProperty("Authorization", TOKEN_ACCESS);
		httpUrlConnection.setUseCaches(false);
		httpUrlConnection.setDoOutput(true);
		if(data!=null){
			httpUrlConnection.setRequestProperty("Content-Length", "" + Integer.toString(data.getBytes().length));
			DataOutputStream wr = new DataOutputStream(httpUrlConnection.getOutputStream());
			wr.writeBytes(data);
			wr.flush();
			wr.close();
		}
		httpUrlConnection.getResponseCode();
		return httpUrlConnection;
	}
	
	protected int getResponseCode(String uri) throws Exception {
		HttpURLConnection httpURLConnection = get(uri);
		httpURLConnection.setRequestProperty("Accept", "application/json");
		return httpURLConnection.getResponseCode();
	}
	
	
	protected String convertFromObjectToJson(Object object) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		return  mapper.writeValueAsString(object);
		
	}
	
	protected <T> T convertFromJsonToObject(String json, Class<T> clazz) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.reader().forType(clazz).readValue(json);
//			return mapper.reader(clazz).readValue(json);
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}
	
	protected <T> T convertFromJsonToListObjects( String json, TypeReference<T> type) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
			objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
			return objectMapper.reader().forType(type).readValue(json);
//			return objectMapper.reader(type).readValue(json);
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}
	
	protected String getAbsoluteUrl(String uri){
		return StringUtils.join(BASE_REST_URI,uri);
	}

	protected void initCustomerAndToken() {
		Customer customer = new Customer();
		customer.setFirstName(CUSTOMER_FIRST_NAME);
		customer.setMail(CUSTOMER_MAIL);
		customer.setName(CUSTOMER_NAME);
		customer.setPassword(CUSTOMER_PASSWORD);
		customer.setSalt(CUSTOMER_SALT);
		customer.setPhone(CUSTOMER_PHONE);
		customer.setRole(roleDao.findRoleByCode(ROLE_CUSTOMER.name()));
		customerDao.create(customer);
		
		CustomerCriteria customerCriteria = new CustomerCriteria();
		List<Customer> customers = customerDao.findCustomersByCriteria(customerCriteria);
		Customer customerResult = customers.get(0);
		Token token = new Token();
		token.setCustomer(customerResult);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 60);
		token.setExpirationDate(now.getTime());
		token.setAccessToken(TOKEN_ACCESS);
		tokenDao.create(token);
	}
	
}
