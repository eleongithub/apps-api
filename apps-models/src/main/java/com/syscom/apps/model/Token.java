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
	private String value;
	private Date expiration;
	private Customer customer;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}
