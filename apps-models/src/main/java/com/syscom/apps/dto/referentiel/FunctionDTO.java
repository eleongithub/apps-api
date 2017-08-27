package com.syscom.apps.dto.referentiel;

import java.io.Serializable;

import com.syscom.apps.model.referential.Function;

public class FunctionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	
	public FunctionDTO(){
		
	}
	
	public FunctionDTO(Function function){
		this.code = function.getCode();
		this.name = function.getName();
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
