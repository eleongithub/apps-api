package com.syscom.apps.model;

import com.syscom.apps.BaseBean;

/**
 * Classe de log des APIs appelés par les clients du Web Service
 * 
 * @author Eric LEGBA
 *
 */
public class LogAPI extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String codeAPI;
	private String nameAPI;
	private String version;

	public LogAPI() {
		
	}
	
	public LogAPI(String codeAPI, String version){
		this.codeAPI = codeAPI;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeAPI() {
		return codeAPI;
	}

	public void setCodeAPI(String codeAPI) {
		this.codeAPI = codeAPI;
	}

	public String getNameAPI() {
		return nameAPI;
	}

	public void setNameAPI(String nameAPI) {
		this.nameAPI = nameAPI;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
