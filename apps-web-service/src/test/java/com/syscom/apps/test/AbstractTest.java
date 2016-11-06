package com.syscom.apps.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/syscom/apps/dao/test-daoContext.xml", "classpath:mybatisConfiguration.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,DbCleanerTestListener.class})
public class AbstractTest {

}
