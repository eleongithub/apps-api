package com.syscom.apps.business.service.impl;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.dao.CustomerDao;
import com.syscom.apps.exception.AppsError;
import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.model.Customer;
import com.syscom.apps.service.interfaces.CustomerService;
import com.syscom.apps.web.service.models.CustomerWS;

@Service("customerService")
public class CustomerServiceImpl extends AbstractService implements
		CustomerService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CustomerDao customerDao;

	/**
	 * 
	 * Guava Function to convert CustomerWS Object to Customer Object
	 * 
	 */
	private static Function<CustomerWS, Customer> CONVERT_FROM_CUSTOMERWS_TO_CUSTOMER = new Function<CustomerWS, Customer>() {
		@Override
		public Customer apply(CustomerWS customerWS) {
			if (customerWS == null) {
				return null;
			}
			Customer customer = new Customer();
			customer.setId(customerWS.getId());
			customer.setName(customerWS.getName());
			customer.setFirstName(customerWS.getFirstName());
			customer.setMail(customerWS.getMail());
			customer.setPhone(customerWS.getPhone());
			customer.setLogin(customerWS.getLogin());
			return customer;
		}
	};
	
	private static Function<Customer, CustomerWS> CONVERT_FROM_CUSTOMER_TO_CUSTOMERWS = new Function<Customer, CustomerWS>() {
		@Override
		public CustomerWS apply(Customer customer) {
			if (customer == null) {
				return null;
			}
			CustomerWS customerWS = new CustomerWS();
			customerWS.setId(customer.getId());
			customerWS.setName(customer.getName());
			customerWS.setFirstName(customer.getFirstName());
			customerWS.setMail(customer.getMail());
			customerWS.setPhone(customer.getPhone());
			customerWS.setLogin(customer.getLogin());
			return customerWS;
		}
	};
	

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void create(CustomerWS customerWS) throws BusinessException {
		if (customerWS == null) {
			return;
		}
		List<AppsError> appsErrors = new ArrayList<>();

		// Check login
		if (isEmpty(customerWS.getLogin())) {
			String message = getMessage("create.customer.login.null.error");
			appsErrors.add(new AppsError(null, message));
		}

		// check mail
		if (isEmpty(customerWS.getMail())) {
			String message = getMessage("create.customer.mail.null.error");
			appsErrors.add(new AppsError(null, message));
		}

		// Check phone
		if (isEmpty(customerWS.getPhone())) {
			String message = getMessage("create.customer.phone.null.error");
			appsErrors.add(new AppsError(null, message));
		}

		// Check Name + fisrtname
		if (isEmpty(customerWS.getName()) || isEmpty(customerWS.getFirstName())) {
			String message = getMessage("create.customer.name.firstName.null.error");
			appsErrors.add(new AppsError(null, message));
		}
		if (!appsErrors.isEmpty()) {
			throw new BusinessException(appsErrors);
		}

		// Check Exist login, mail, telephone, name + firstName
		CustomerCriteria criteria = new CustomerCriteria();
		criteria.setName(customerWS.getName());
		criteria.setFirstName(customerWS.getFirstName());
		criteria.setMail(customerWS.getMail());
		criteria.setPhone(customerWS.getPhone());
		criteria.setLogin(customerWS.getLogin());
		List<Customer> customers = customerDao.findCustomersByCriteria(criteria);
		
		if(customers!=null && customers.size()>0){
			boolean usedLogin = false;
			boolean usedMail =  false;
			boolean usedPhone = false;
			boolean usedNameFirstName = false;
			for(Customer customer: customers){
				if(!usedLogin){
					if(StringUtils.equals(customerWS.getLogin(), customer.getLogin())){
						appsErrors.add(new AppsError(null, getMessage("create.customer.login.used.error")));
						usedLogin=true;
					}
				}
				
				if(!usedMail){
					if(StringUtils.equals(customerWS.getMail(), customer.getMail())){
						appsErrors.add(new AppsError(null, getMessage("create.customer.mail.used.error")));
						usedMail=true;
					}
				}
				
				if(!usedPhone){
					if(StringUtils.equals(customerWS.getPhone(), customer.getPhone())){
						appsErrors.add(new AppsError(null, getMessage("create.customer.phone.used.error")));
						usedPhone=true;
					}
				}
				
				if(!usedNameFirstName){
					if(StringUtils.equals(customerWS.getName(), customer.getName())
					  && StringUtils.equals(customerWS.getFirstName(), customer.getFirstName())		
						){
						appsErrors.add(new AppsError(null, getMessage("create.customer.name.firstName.used.error")));
						usedNameFirstName=true;
					}
				}
			}
		}

		if(!appsErrors.isEmpty()){
			throw new BusinessException(appsErrors);
		}
		customerDao.create(CONVERT_FROM_CUSTOMERWS_TO_CUSTOMER.apply(customerWS));
	}

	
	@Override
	public CustomerWS update(CustomerWS customer){
		if(customer==null){
			return null;
		}
		Customer convertCustomer = CONVERT_FROM_CUSTOMERWS_TO_CUSTOMER.apply(customer);
		customerDao.update(convertCustomer);
		CustomerWS customerWS = CONVERT_FROM_CUSTOMER_TO_CUSTOMERWS.apply(customerDao.findCustomerById(customer.getId()));
		return customerWS;
	}

	
	@Override
	public void delete(Long id) {
		if(id == null){
			return ;
		}
		customerDao.delete(id);
	}

}