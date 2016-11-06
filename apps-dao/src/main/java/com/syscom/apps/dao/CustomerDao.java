package com.syscom.apps.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.model.Customer;

/**
 * 
 * @author ELE1638EN
 *
 */
public interface CustomerDao {
	
	/**
	 * 
	 * Insert new customer
	 * 
	 * @param customer {@link Customer}
	 */
	void create(@Param("customer")Customer customer);
	
	
	/**
	 * 
	 * Update customer
	 * 
	 * @param customer {@link Customer}
	 */
	void update(@Param("customer")Customer customer);
	

	/**
	 * 
	 * Delete customer
	 * 
	 * @param id {@link Long}
	 */
	void delete(@Param("id")Long id);
	
	/**
	 * 
	 * Find customer by ID
	 * 
	 * @param id {@link Long}
	 * @return customer
	 */
	Customer findCustomerById(@Param("id")Long id);
	
	
	/**
	 * 
	 * Find customers
	 * 
	 * @param criteria {@link CustomerCriteria}
	 * @return List of customers
	 */
	List<Customer> findCustomersByCriteria(@Param("criteria")CustomerCriteria criteria);
	
	
	List<Customer> getAll();

}
