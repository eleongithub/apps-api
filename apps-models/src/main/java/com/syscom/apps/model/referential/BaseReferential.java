package com.syscom.apps.model.referential;

import com.syscom.apps.BaseBean;

public class BaseReferential extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Long id;
	protected String name;
	
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
	

}
