package com.syscom.apps.dto.referentiel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.syscom.apps.model.referential.Role;

public class RoleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String code;
	private List<FunctionDTO> functionDTOs;

	public RoleDTO(){
		functionDTOs = new ArrayList<>();	
	}
	
	public RoleDTO(Role role){
		this.name = role.getName();
		this.code = role.getCode();
		functionDTOs = new ArrayList<>();
		if(role.getFunctions()!=null){
			functionDTOs = role.getFunctions().stream().map(function -> new FunctionDTO(function))
			                   				  .collect(Collectors.toList());
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public List<FunctionDTO> getFunctionDTOs() {
		return functionDTOs;
	}

	public void setFunctionDTOs(List<FunctionDTO> functionDTOs) {
		this.functionDTOs = functionDTOs;
	}
}
