package com.syscom.apps.web.config.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.exception.TechnicalException;

public class TraceServiceInvocation {
	private static Logger LOGGER = Logger.getLogger(TraceServiceInvocation.class);

	public Object logServiceMethod(final ProceedingJoinPoint joinpoint)
			throws Throwable {
		String methodName = joinpoint.getTarget().getClass().getSimpleName()
				+ "." + joinpoint.getSignature().getName();

		final Object[] args = joinpoint.getArgs();
		final StringBuilder sb = new StringBuilder();
		sb.append(joinpoint.getSignature().toString());
		sb.append(" with parameters : (");

		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");
		LOGGER.info("Begin method : " + sb);
		Object object = null;
		long startTime = System.currentTimeMillis();
		try {
			object = joinpoint.proceed();
		}catch (BusinessException businessException) {
			LOGGER.error(businessException.getMessage());
			throw businessException;
		}catch (TechnicalException technicalException) {
			LOGGER.error(technicalException.getMessage());
			throw technicalException;
		}catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			throw exception;
		} finally {
			long duration = System.currentTimeMillis() - startTime;
			LOGGER.info("End method : " + methodName + " returns=" + object
					+ ".Duration :" + duration + " ms");
		}
		return object;
	}
}
