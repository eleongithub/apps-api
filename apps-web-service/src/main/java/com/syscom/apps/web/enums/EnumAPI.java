package com.syscom.apps.web.enums;

public enum EnumAPI {

	LOGIN_CUSTOMER("login.customer"), //
	CREATE_CUSTOMER("create.customer"), //
	UPDATE_CUSTOMER("update.customer"), //
	CONSULT_CUSTOMER("consult.customer"), //
	CREATE_ADVERT("create.advert"), //
	;

	private String key;

	EnumAPI(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
