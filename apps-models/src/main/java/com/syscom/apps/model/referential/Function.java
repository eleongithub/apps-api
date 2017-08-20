package com.syscom.apps.model.referential;


/**
 * 
 * Function
 *
 */
public class Function extends BaseReferential{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getAuthority() {
		return this.code;
	}

}