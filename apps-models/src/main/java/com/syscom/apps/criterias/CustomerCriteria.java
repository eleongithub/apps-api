package com.syscom.apps.criterias;

import java.io.Serializable;

/**
 * Classe contenant les critères de recherche d'un utilisateur
 * 
 * 
 * @author Eric LEGBA
 *
 */
public class CustomerCriteria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String firstName;
	private String mail;
	private String password;
	private String phone;
	
	/**
	 * Constructeur par défaut
	 * 
	 */
	public CustomerCriteria(){
		
	}
	
	/**
	 * 
	 * Builder pour construire les critères de recherche
	 * 
	 * @author Eric LEGBA
	 *
	 */
	public static class Builder {

		private Long id;
		private String name;
		private String firstName;
		private String mail;
		private String password;
		private String phone;
		
		public Builder id(Long id) {
            this.id = id;
            return this;
        }
		
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder mail(String mail) {
            this.mail = mail;
            return this;
        }
        
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        

        public CustomerCriteria build() {
            return new CustomerCriteria(this);
        }

    }

	/**
	 * 
	 * Constructeur des critères de recherche à partir du builder.
	 * 
	 * @param builder {@link Builder}
	 */
    private CustomerCriteria(Builder builder) {
        id = builder.id;
        name = builder.name;
        firstName = builder.firstName;
        mail = builder.mail;
        password = builder.password;
        phone = builder.phone;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	
}
