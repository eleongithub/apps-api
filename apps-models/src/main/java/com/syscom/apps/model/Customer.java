package com.syscom.apps.model;

import java.util.Date;

public class Customer extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String salt;
	private boolean changePassword;
	private boolean frozenAccount;
	private Date frozenDate;
	private String originFrozenMsg;
	private Date lastFailureConnexionDate;

	public Customer(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	public Date getFrozenDate() {
		return frozenDate;
	}

	public void setFrozenDate(Date frozenDate) {
		this.frozenDate = frozenDate;
	}

	public String getOriginFrozenMsg() {
		return originFrozenMsg;
	}

	public boolean isFrozenAccount() {
		return frozenAccount;
	}

	public void setFrozenAccount(boolean frozenAccount) {
		this.frozenAccount = frozenAccount;
	}

	public void setOriginFrozenMsg(String originFrozenMsg) {
		this.originFrozenMsg = originFrozenMsg;
	}
	
	public Date getLastFailureConnexionDate() {
		return lastFailureConnexionDate;
	}

	public void setLastFailureConnexionDate(Date lastFailureConnexionDate) {
		this.lastFailureConnexionDate = lastFailureConnexionDate;
	}


}
