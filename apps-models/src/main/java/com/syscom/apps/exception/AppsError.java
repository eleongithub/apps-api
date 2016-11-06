package com.syscom.apps.exception;

import java.io.Serializable;

public class AppsError implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String key;
	private Object[] args;
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
	
	public AppsError(String key, String message, Object... args){
		this.key = key;
		this.message = message;
		this.args = args;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
