package com.syscom.apps.dto;

import java.io.Serializable;
import com.syscom.apps.dto.referentiel.RoleDTO;
import com.syscom.apps.model.Customer;


/**
 * Classe DTO représentant les données d'un utilisateur
 * 
 * 
 * @author Eric LEGBA
 */
public class CustomerDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String firstName;
	private String mail;
	private String phone;
	private String password;
	private RoleDTO roleDTO;
	
	public CustomerDTO(){
		
	}
	
	public CustomerDTO(Customer customer){
		this.id = customer.getId();
		this.name = customer.getName();
		this.firstName = customer.getFirstName();
		this.mail = customer.getMail();
		this.phone = customer.getPhone();
		this.password = customer.getPassword();
		if(customer.getRole()!=null){
			roleDTO = new RoleDTO(customer.getRole());
		}
	}
	
	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
