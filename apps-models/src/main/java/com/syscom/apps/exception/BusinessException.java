package com.syscom.apps.exception;



/**
 * Exception fonctionnelle
 * 
 * @author Eric LEGBA
 *
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int statusCode;
	
	private String message;
	
	
	/**
	 * 
	 * Constructeur par défaut.
	 * 
	 */
	public BusinessException(){
		super();
	}
	
	/**
	 * 
	 * Constructeur.
	 *  
	 * @param statusCode code status HTTP.
	 */
	public BusinessException(int statusCode){
		super();
		this.statusCode = statusCode;
	}
	
	/**
	 * Constructeur.
	 *
	 * @param message of exception.
	 */
	public BusinessException(String message) {
		super(message);
		this.message = message;
	}
	
	/**
	 * 
	 * Constructeur.
	 *  
	 * @param statusCode code status HTTP.
	 * @param message of exception.
	 */
	public BusinessException(int statusCode, String message){
		super(message);
		this.statusCode = statusCode;
		this.message = message;
	}
	

	/**
	 * Constructeur.
	 *
	 * @param message of exception.
	 * @param cause of exception.
	 */
	public BusinessException(String message, final Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	/**
	 * Constructeur.
	 *
	 * @param cause of exception.
	 */
	public BusinessException(Throwable cause) {
		super(cause);
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
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


}
