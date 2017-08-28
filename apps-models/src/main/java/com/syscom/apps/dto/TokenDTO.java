package com.syscom.apps.dto;

import java.io.Serializable;
import java.util.Date;
import com.syscom.apps.dto.referentiel.RoleDTO;
import com.syscom.apps.model.Customer;
import com.syscom.apps.model.Token;


/**
 * Classe DTO représentant les données d'un jeton d'authentification
 * 
 * @author Eric LEGBA
 */
public class TokenDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String value;
	private Date expiration;
	private CustomerDTO customerDTO;
	
	public TokenDTO(){
		
	}
	
	public TokenDTO(Token token){
		this.value = token.getValue();
		this.expiration = token.getExpiration();
		customerDTO = new CustomerDTO();
		Customer customer = token.getCustomer();
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setMail(customer.getMail());
		customerDTO.setPhone(customer.getPhone());
		customerDTO.setRoleDTO(new RoleDTO(customer.getRole()));
		
	}
	
	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}
	
	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public Date getExpiration() {
		return expiration;
	}
	
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
}
