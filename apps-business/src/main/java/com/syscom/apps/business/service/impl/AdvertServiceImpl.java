package com.syscom.apps.business.service.impl;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.syscom.apps.business.service.AdvertService;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.dao.AdvertDao;
import com.syscom.apps.dao.CustomerDao;
import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.model.Advert;
import com.syscom.apps.model.Customer;

/** {@inheritDoc} */
@Service("advertService")
@Transactional(rollbackFor=Exception.class)
public class AdvertServiceImpl extends BaseService implements AdvertService {

	
	@Autowired
	private AdvertDao advertDao;

	@Autowired
	private CustomerDao customerDao;
	

	/** {@inheritDoc} 
	 * @throws IOException */
	@Override
	public void create(Advert advert) throws BusinessException {
		List<String> appsErrors = this.checkInputDatas(advert);
		
		if(!appsErrors.isEmpty()){
			String message = getMessage("create.empty.error")+Joiner.on(",").join(appsErrors);
			throw new BusinessException(message);
		}
		Customer customer = advert.getCustomer();
		// Check Exist login, mail, telephone, name + firstName
		CustomerCriteria criteria = new CustomerCriteria.Builder()
														.id(customer.getId())
														.name(customer.getName())
														.firstName(customer.getFirstName())
														.mail(customer.getMail())
														.phone(customer.getPhone())
														.build();
		List<Customer> customers = customerDao.findCustomersByCriteria(criteria);
		if(CollectionUtils.isEmpty(customers)){
			throw new BusinessException(getMessage("create.advert.unknown.customer"));
		}
		advert.setCustomer(customers.get(0));
		advertDao.create(advert);	
		
	}

	private List<String> checkInputDatas(Advert advert) {
		List<String> appsErrors = new ArrayList<>();

		if (isEmpty(advert.getDescription())) {
			appsErrors.add(getMessage("create.advert.description"));
		}

		if (advert.getPrice() == null || advert.getPrice() == 0) {
			appsErrors.add(getMessage("create.advert.price"));
		}

		if (isEmpty(advert.getTitle())) {
			appsErrors.add(getMessage("create.advert.title"));
		}

		if (advert.getCustomer() == null) {
			appsErrors.add(getMessage("create.advert.customer"));
		}

		return appsErrors;
	}

}
