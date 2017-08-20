package com.syscom.apps.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * Classe DTO représentant les données d'un token
 * 
 * 
 * @author Eric LEGBA
 *
 */
public class TokenDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accessToken;
	private Date expiration;
	private CustomerDTO customerDTO;
	
	
	
	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}
	
	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public Date getExpiration() {
		return expiration;
	}
	
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	
}
