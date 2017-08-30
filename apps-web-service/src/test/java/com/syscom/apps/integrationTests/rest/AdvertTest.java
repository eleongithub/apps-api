package com.syscom.apps.integrationTests.rest;

import java.net.HttpURLConnection;

import javax.ws.rs.core.Response;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.syscom.apps.dto.AdvertDTO;
import com.syscom.apps.integrationTests.AbstractWebServiceTest;

public class AdvertTest extends AbstractWebServiceTest {
	
//	TODO à regarder !! http://jenkins-le-guide-complet.github.io/html/appendix-automating-your-tests.html

	
	@Test
	public void testCreateEmptyAdvert() throws Exception {
		initCustomerAndToken();
		HttpURLConnection connection = postAPIRequest("/advert", convertFromObjectToJson(new AdvertDTO()));
		Assertions.assertThat(connection.getResponseCode()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
	}


	
}
