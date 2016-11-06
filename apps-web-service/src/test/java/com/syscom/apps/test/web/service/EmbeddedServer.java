package com.syscom.apps.test.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;

public class EmbeddedServer {

	private static final int PORT = 9090;
	private static Server jettyServer;

	static {
		try {
			start();
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

	public static void start() throws Exception {
		jettyServer = new Server(PORT);
		WebAppContext context = new WebAppContext();
		context.setServer(jettyServer);
		context.setContextPath("/");
		context.setResourceBase("src/main/webapp");
		jettyServer.setHandler(context);
		XmlConfiguration xmlConfiguration = new XmlConfiguration(new FileInputStream(new File("src/test/resources/jetty.xml")));
		xmlConfiguration.configure(context);
		context.setServer(jettyServer);
		jettyServer.start();
	}

	protected URL getUrl(String url) throws IOException {
		return new URL("http://localhost:"+PORT+"/" + url);
	}

	public static void stop() throws Exception {
		jettyServer.stop();
	}
}
