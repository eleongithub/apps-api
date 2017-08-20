package com.syscom.apps.model;

import com.syscom.apps.BaseBean;

/**
 * Classe représentant les données d'une personne
 * 
 * @author Eric LEGBA
 *
 */
public class Person extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String name;
	protected String firstName;
	protected String mail;
	protected String phone;
	protected String password;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
