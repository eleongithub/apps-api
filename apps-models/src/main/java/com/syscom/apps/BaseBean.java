package com.syscom.apps;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * BaseBean
 *
 */
public class BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Date createDate;
	protected Date updateDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
