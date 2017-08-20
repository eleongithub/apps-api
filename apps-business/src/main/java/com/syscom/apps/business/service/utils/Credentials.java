package com.syscom.apps.business.service.utils;

import java.io.Serializable;

/**
 * Credentials regroupant le couple mot de passe - sel de contrôle
 * 
 * @author Eric LEGBA
 *
 */
public class Credentials implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String password;
	private String salt;

	/**
	 * Constructeur par défaut
	 * 
	 */
	public Credentials(){
		
	}
	
	/**
	 * Contructeur avec paramètre
	 * 
	 * @param password : mot de passe
	 * @param salt : sel de contrôle
	 * @author Eric LEGBA
	 */
	public Credentials(String password, String salt){
		this.password = password;
		this.salt = salt;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	

}
