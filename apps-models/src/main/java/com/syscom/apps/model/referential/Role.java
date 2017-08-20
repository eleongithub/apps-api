package com.syscom.apps.model.referential;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Role
 *
 */
public class Role extends BaseReferential{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private List<Function> functions;
	
	public Role(){
		functions = new ArrayList<>();	
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
}
