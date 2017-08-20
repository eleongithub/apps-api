package com.syscom.apps.exception;


/**
 * Exception technique
 * 
 * @author Eric LEGBA
 *
 */
public class TechnicalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	

	/**
	 * Constructeur.
	 *
	 * @param message of exception.
	 */
	public TechnicalException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Constructeur.
	 *
	 * @param message of exception.
	 * @param cause of exception.
	 */
	public TechnicalException(String message, final Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	/**
	 * Constructeur.
	 *
	 * @param cause of exception.
	 */
	public TechnicalException(Throwable cause) {
		super(cause);
		this.message = cause.getMessage();
	}

	/** {@inheritDoc} */
	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return super.initCause(this);
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
