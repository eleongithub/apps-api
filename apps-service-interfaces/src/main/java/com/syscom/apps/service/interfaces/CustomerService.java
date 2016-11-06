package com.syscom.apps.service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.web.service.models.CustomerWS;

public interface CustomerService {
	
	/**
	 * 
	 * Create new customer
	 * 
	 * @param customer {@link CustomerWS}
	 * @throws BusinessException {@link BusinessException}
	 */
	@WebMethod(operationName="create")
	public void create(@WebParam(name="customer")CustomerWS customer) throws BusinessException;
	
	
	/**
	 * 
	 * Update customer
	 * 
	 * @param customer {@link CustomerWS}
	 * @return customer {@link CustomerWS}
	 */
	@WebMethod(operationName="update")
	public CustomerWS update(@WebParam(name="customer")CustomerWS customer);
	
	
	/**
	 * 
	 * Delete customer
	 * 
	 * @param customer {@link CustomerWS}
	 */
	@WebMethod(operationName="delete")
	public void delete(@WebParam(name="id")Long id);

}
