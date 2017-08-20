package com.syscom.apps.exception;

import java.io.Serializable;

/**
 * Classe d'une erreur fonctionnelle
 * 
 * @author Eric LEGBA
 *
 */
public class AppsError implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String key;
	private String message;

	public AppsError(){

	}
	
	public AppsError(String key){
		this.key = key;
	}
	
	public AppsError(String key, String message){
		this.key = key;
		this.message = message;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
