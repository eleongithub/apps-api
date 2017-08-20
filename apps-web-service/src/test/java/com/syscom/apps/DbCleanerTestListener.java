package com.syscom.apps;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.dbmaintain.DbMaintainer;
import org.dbmaintain.MainFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;


public class DbCleanerTestListener implements TestExecutionListener {
	
	@Override
	public void afterTestClass(TestContext testContext) throws Exception {

	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {

	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {

	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		ApplicationContext appContext = testContext.getApplicationContext();
		Resource resource = appContext.getResource("dbmaintain.properties");	
    	Properties property = PropertiesLoaderUtils.loadProperties(resource);
//		Delete old schema and create a new clean schema for each test case.
		DataSource ds = appContext.getBean(DataSource.class);
		try (Connection connection = ds.getConnection()) {	
			Statement statement = connection.createStatement();
			String user = property.getProperty("database.userName");
			String schema = property.getProperty("database.schemaNames");
			statement.executeUpdate(String.format("DROP SCHEMA %s CASCADE",schema));
			statement.executeUpdate(String.format("CREATE SCHEMA %s AUTHORIZATION %s",schema,user));
			statement.close();
			connection.close();
		}
		
//		Execute Dbmaintain scripts into the new schema.
        MainFactory mainFactory = new MainFactory(property);
        DbMaintainer dbMaintainer = mainFactory.createDbMaintainer();
        dbMaintainer.updateDatabase(false);
	}

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {

	}

}
