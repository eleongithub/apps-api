package com.syscom.apps.business.service.impl;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.google.common.base.Joiner;
import com.syscom.apps.business.service.AdvertService;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.dao.AdvertDao;
import com.syscom.apps.dao.CustomerDao;
import com.syscom.apps.dto.AdvertDTO;
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
	

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public void create(AdvertDTO advertDTO) throws BusinessException{
		
		Assert.notNull(advertDTO,getMessage("advert.mandatory"));
		
		Advert advert = new Advert();
		advert.setDescription(advertDTO.getDescription());
		advert.setPrice(advertDTO.getPrice());
		advert.setTitle(advertDTO.getTitle());
		if(advertDTO.getCustomerDTO()!=null){
			Customer  customer = new Customer();
			customer.setId(advertDTO.getCustomerDTO().getId());
			advert.setCustomer(customer);
		}
		
		List<String> appsErrors = checkInputDatas(advert);
		
		if(!appsErrors.isEmpty()){
			String message = getMessage("create.empty.error")+Joiner.on(",").join(appsErrors);
			throw new BusinessException(message);
		}
		// Check Exist customer
		CustomerCriteria criteria = new CustomerCriteria.Builder()
														.id(advert.getCustomer().getId())
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
