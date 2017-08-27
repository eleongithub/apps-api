package com.syscom.apps.model;

import com.syscom.apps.dto.CustomerDTO;
import com.syscom.apps.model.referential.Role;

/**
 * Classe des utilisateurs
 * 
 * @author Eric LEGBA
 */
public class Customer extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String salt;
	private Role role;

	public Customer(){
		
	}
	
	public Customer(CustomerDTO customerDTO){
		this.id = customerDTO.getId();
		this.name = customerDTO.getName();
		this.firstName = customerDTO.getFirstName();
		this.mail = customerDTO.getMail();
		this.phone = customerDTO.getPhone();
		this.password = customerDTO.getPassword();
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
