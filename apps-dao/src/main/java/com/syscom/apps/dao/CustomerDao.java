package com.syscom.apps.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.model.Customer;

/**
 * DAO pour gérer la persistence des utilisateurs
 * 
 * @author Eric LEGBA
 *
 */
public interface CustomerDao {
	
	/**
	 * Enregistrer un nouvel utilisateur
	 * 
	 * @param customer {@link Customer}
	 */
	void create(@Param("customer")Customer customer);
	
	
	/**
	 * Modifier un utilisateur
	 * 
	 * @param customer {@link Customer}
	 * @author Eric LEGBA
	 */
	void update(@Param("customer")Customer customer);
	

	/**
	 * Supprimer un utilisateur
	 * 
	 * @param id {@link Long}
	 * @author Eric LEGBA
	 */
	void delete(@Param("id")Long id);
	

	
	/**
	 * Rechercher un utilisateur à partir de son mail
	 * 
	 * @param mail {@link String}
	 * @return customer
	 * @author Eric LEGBA
	 */
	Customer findCustomerByMail(@Param("mail")String mail);
	
	
	/**
	 * Recherche des utilisateurs à partir de critères
	 * 
	 * @param criteria {@link CustomerCriteria}
	 * @return List of customers
	 * @author Eric LEGBA
	 */
	List<Customer> findCustomersByCriteria(@Param("criteria")CustomerCriteria criteria);

	/**
	 * Check exist customers
	 * 
	 * @param criteria {@link CustomerCriteria}
	 * @return List of customers
	 * @author Eric LEGBA
	 */
	List<Customer> checkExistsCustomerByCriteria(@Param("criteria")CustomerCriteria criteria);
	
	
	/**
	 * Check exist customer by mail
	 * 
	 * @param mail 
	 * @return boolean
	 * @author Eric LEGBA
	 */
	
	boolean existsCustomerByMail(@Param("mail")String mail);
	
	
	/**
	 * Check exist customer by phone number
	 * 
	 * @param mail 
	 * @return boolean
	 * @author Eric LEGBA
	 */
	
	boolean existsCustomerByPhone(@Param("phone")String phone);
	
	
	/**
	 * Check exist customer by name and first name
	 * 
	 * @param name 
	 * @param firstName 
	 * @return boolean
	 * @author Eric LEGBA
	 */
	
	boolean existsCustomerByNameFirstName(@Param("name")String name, @Param("firstName")String firstName);
	
	

}
