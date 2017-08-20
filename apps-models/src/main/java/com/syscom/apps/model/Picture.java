package com.syscom.apps.model;

import javax.activation.DataHandler;

import com.syscom.apps.BaseBean;

/**
 * Classe représentant les données d'une image d'une annonce
 * 
 * @author Eric LEGBA
 *
 */
public class Picture extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String address;
	private transient DataHandler dataHandler;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public DataHandler getDataHandler() {
		return dataHandler;
	}

	public void setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}
	
}
