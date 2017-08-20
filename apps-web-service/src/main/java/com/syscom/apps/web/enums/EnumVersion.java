package com.syscom.apps.web.enums;

public enum EnumVersion {
	V1("v1"),//
	;
	private String version;

	EnumVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
