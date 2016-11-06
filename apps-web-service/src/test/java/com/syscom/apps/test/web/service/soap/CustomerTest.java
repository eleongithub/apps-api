package com.syscom.apps.test.web.service.soap;

import java.net.HttpURLConnection;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import com.syscom.apps.test.web.service.AbstractWebServiceTest;

public class CustomerTest extends AbstractWebServiceTest{

	@Test
	public void test() throws Exception {
//	String url = "rest/api/user/getall";
//	HttpURLConnection connection = get(url);
//	Assertions.assertThat(connection.getResponseCode()).describedAs("Wrong HTTP status response for %s",
//					url).isEqualTo(200);
//	
//	String ctxPath = "/echo";
//	String epPath = "/service";
//	String endpointAddr = "WS-SOAP" + ctxPath + epPath;
//
//	JAXWSHandler handler = new JAXWSHandler();
//	server.setHandler(handler);
//	server.start();
//
//	try {
//		JettyHttpContext context = handler.register(ctxPath, epPath);
//		Endpoint endpoint = Endpoint.create(new EchoServiceImpl());
//		endpoint.publish(context);
//
//		URL wsdlURL = new URL(endpointAddr + "?wsdl");
//		HttpURLConnection wsdlConnection = (HttpURLConnection) wsdlURL.openConnection();
//		wsdlConnection.connect();
//		assertEquals(HttpServletResponse.SC_OK, wsdlConnection.getResponseCode());
//		StringBuilder wsdl = new StringBuilder();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(wsdlConnection.getInputStream()));
//		String line = null;
//		while ((line = reader.readLine()) != null) {
//			wsdl.append(line);
//			wsdl.append("\n");
//		}
//		//System.out.println(wsdl);
//		assertTrue(wsdl.toString().contains("name=\"EchoService\""));
//
//		QName serviceName = new QName(EchoService.TNS, EchoServiceImpl.SERVICE_NAME);
//		QName portName = new QName(EchoService.TNS, EchoServiceImpl.PORT_NAME);
//		// Service service = Service.create(serviceName);
//		// service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING,
//		// endpointAddr);
//		Service service = Service.create(wsdlURL, serviceName);
//		EchoService echoService = (EchoService) service.getPort(portName, EchoService.class);
//		assertEquals("echo: test message", echoService.echo("test message"));
//
//		endpoint.stop();
//		handler.unregister(context);
//	} finally {
//		server.stop();
	}
}
