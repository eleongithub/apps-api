package com.syscom.apps.model;

import com.syscom.apps.BaseBean;

/**
 * Classe des annonces
 * 
 * 
 * @author Eric LEGBA
 *
 */
public class Advert extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String description;
	private Float price;
	private Customer customer;
	

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


}
