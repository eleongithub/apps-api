package com.syscom.apps.model;

import java.util.ArrayList;
import java.util.List;

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
	private List<Picture> pictures;
	
	/**
	 * Constructeur par défaut
	 * 
	 */
	public Advert(){
		pictures = new ArrayList<>();
	}
	
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

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

}
