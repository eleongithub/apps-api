package com.syscom.apps.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.util.StringUtils;


public class DbCleanerTestListener implements TestExecutionListener {
	
	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		ApplicationContext appContext = testContext.getApplicationContext();
		DataSource ds = appContext.getBean(DataSource.class);
		try (Connection conn = ds.getConnection()) {				
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select tablename from pg_tables where schemaname='appsdbtest' and tablename <> 'dbmaintain_scripts'");
			List<String> tables = new ArrayList<>();
			while (rs.next()) {
				tables.add(rs.getString(1));
			}
			String request = String.format("TRUNCATE TABLE %s RESTART identity;",
					StringUtils.collectionToCommaDelimitedString(tables));
			stmt.execute(request);
		}
	}

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		// TODO Auto-generated method stub

	}

}
