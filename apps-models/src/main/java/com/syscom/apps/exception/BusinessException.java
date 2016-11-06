package com.syscom.apps.exception;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author ELE1638EN
 *
 */
public class BusinessException extends Exception implements SyscomException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AppsError> errors;

	public BusinessException(AppsError... erreurs) {
		this.errors = Arrays.asList(erreurs);
	}

	public BusinessException(List<AppsError> erreurs) {
		this.errors = erreurs;
	}

	@Override
	public List<AppsError> getErrors() {
		return errors;
	}
	

	/**
	 * Constructor.
	 *
	 * @param message of exception.
	 */
	public BusinessException(final String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message of exception.
	 * @param cause of exception.
	 */
	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor.
	 *
	 * @param cause of exception.
	 */
	public BusinessException(final Throwable cause) {
		super(cause);
	}

	/** {@inheritDoc} */
	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return super.initCause(this);
	}

}
