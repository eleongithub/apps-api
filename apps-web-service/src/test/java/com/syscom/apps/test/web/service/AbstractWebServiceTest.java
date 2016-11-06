package com.syscom.apps.test.web.service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syscom.apps.test.AbstractTest;

public abstract class AbstractWebServiceTest extends AbstractTest{

	protected static EmbeddedServer server;

	private static final String BASE_REST_URI = "WS-REST/api/";
	static {
		server = new EmbeddedServer();
	}

	protected HttpURLConnection get(String uri) throws Exception {
		URL url = server.getUrl(BASE_REST_URI+uri);
		HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
		httpUrlConnection.setRequestMethod("GET");
		return httpUrlConnection;
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

	
}
