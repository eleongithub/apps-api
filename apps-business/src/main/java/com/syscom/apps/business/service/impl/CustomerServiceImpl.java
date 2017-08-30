package com.syscom.apps.business.service.impl;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.base.Joiner;
import com.syscom.apps.business.service.CustomerService;
import com.syscom.apps.business.service.utils.Credentials;
import com.syscom.apps.business.service.utils.Encryption;
import com.syscom.apps.dao.CustomerDao;
import com.syscom.apps.dao.RoleDao;
import com.syscom.apps.dto.CustomerDTO;
import com.syscom.apps.enums.EnumRole;
import com.syscom.apps.exception.BusinessException;
import com.syscom.apps.exception.TechnicalException;
import com.syscom.apps.model.Customer;
import com.syscom.apps.model.referential.Role;

/** {@inheritDoc} */
@Service("customerService")
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl extends BaseService implements CustomerService {


	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private RoleDao roleDao;

	/** {@inheritDoc} */
	@Override
	public void create(CustomerDTO customerDTO) throws BusinessException {
		
		Assert.notNull(customerDTO,getMessage("customer.mandatory"));
		
		this.checkInputDatas(customerDTO); 	
		
		List<String> businessErrors = new ArrayList<>();
				
		if (customerDao.existsCustomerByMail(customerDTO.getMail())) {
			businessErrors.add(getMessage("create.customer.mail.used.error"));
		}
		 
		if (customerDao.existsCustomerByNameFirstName(customerDTO.getName(), customerDTO.getFirstName())) {
			businessErrors.add(getMessage("create.customer.name.firstName.used.error"));
		}
		 
		if (customerDao.existsCustomerByPhone(customerDTO.getPhone())) {
			businessErrors.add(getMessage("create.customer.phone.used.error"));
		}
		
		if (!businessErrors.isEmpty()) {
			String message = getMessage("create.customer.datas.error")+Joiner.on(",").join(businessErrors);
			throw new BusinessException(message);
		}
		try {
			Customer customer = this.convertToEntity(customerDTO);
			Credentials credentials = generateEncryptCredentials(customer.getPassword());
			customer.setPassword(credentials.getPassword());
			customer.setSalt(credentials.getSalt());
			Role role = roleDao.findRoleByCode(EnumRole.ROLE_CUSTOMER.name());
			customer.setRole(role);
			customerDao.create(customer);
		} catch (NoSuchAlgorithmException algorithmException) {
			throw new TechnicalException(algorithmException);
		}
	}

	/** {@inheritDoc} */
	@Override
	public CustomerDTO update(CustomerDTO customerDTO) throws BusinessException {
		this.checkInputDatas(customerDTO); 
		CustomerDTO result = null;
		try {
			Customer customer = convertToEntity(customerDTO);
			Credentials credentials = generateEncryptCredentials(customer.getPassword());
			customer.setPassword(credentials.getPassword());
			customer.setSalt(credentials.getSalt());
			customerDao.update(customer);
			result = this.convertToDTO(customerDao.findCustomerByMail(customerDTO.getMail()));
		} catch (NoSuchAlgorithmException algorithmException) {
			throw new TechnicalException(algorithmException);
		}

		return result;
	}

	/** {@inheritDoc} */
	@Override
	public void delete(Long id) {
		customerDao.delete(id);
	}

	private Credentials generateEncryptCredentials(String password)throws NoSuchAlgorithmException {
		return Encryption.getInstance().encrypt(password);
	}

	private void checkInputDatas(CustomerDTO customerDto) throws BusinessException {
		List<String> emptiesInputErrors = new ArrayList<>();
		// check mail
		if (isEmpty(customerDto.getMail())) {
			emptiesInputErrors.add(getMessage("create.customer.mail"));
		}

		// Check phone
		if (isEmpty(customerDto.getPhone())) {
			emptiesInputErrors.add(getMessage("create.customer.phone"));
		}

		// Check Name
		if (isEmpty(customerDto.getName())) {
			emptiesInputErrors.add(getMessage("create.customer.name"));
		}

		// Check firstname
		if (isEmpty(customerDto.getFirstName())) {
			emptiesInputErrors.add(getMessage("create.customer.firstName"));
		}

		 if (CollectionUtils.isNotEmpty(emptiesInputErrors)) {
			String message = getMessage("create.empty.error")+Joiner.on(",").join(emptiesInputErrors);
			throw new BusinessException(message);
		}
		
	}

	/**
	 * Conversion d'un objet utilisateur vers son Dto
	 * 
	 * @param customer
	 * @return {@link CustomerDTO}
	 * @author Eric LEGBA
	 */
	private CustomerDTO convertToDTO(Customer customer) {
		return new CustomerDTO(customer);
	}

	/**
	 * Conversion d'un Dto vers l'objet utilisateur
	 * @param customerDTO
	 * @return {@link Customer}
	 * @author Eric LEGBA
	 */
	private Customer convertToEntity(CustomerDTO customerDTO) {
		return new Customer(customerDTO);
	}

}