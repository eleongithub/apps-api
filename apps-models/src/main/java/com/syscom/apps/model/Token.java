package com.syscom.apps.model;

import java.util.Date;

import com.syscom.apps.BaseBean;

/**
 * Token, jeton de sécurité
 * 
 * @author Eric LEGBA
 *
 */
public class Token extends BaseBean{

	/**
	 * 
	 * TODO https://github.com/reddit/reddit/wiki/OAuth2#manually-revoking-a-token
	 */
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String accessToken;
	private Date expirationDate;
	private Customer customer;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	

}
