package com.syscom.apps.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.syscom.apps.criterias.CustomerCriteria;
import com.syscom.apps.model.Customer;

/**
 * Contrat d'interface du DAO pour gérer la persistence des utilisateurs
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
	 * @param customer utilisateur à modifier {@link Customer}
	 */
	void update(@Param("customer")Customer customer);
	

	/**
	 * Supprimer un utilisateur à partir de son identifiant
	 * 
	 * @param id {@link Long} de l'utilisateur
	 */
	void delete(@Param("id")Long id);
	

	
	/**
	 * Rechercher un utilisateur à partir de son mail
	 * 
	 * @param mail adresse mail de l'utilisateur {@link String} 
	 * @return customer utilisateur {@link Customer}
	 */
	Customer findCustomerByMail(@Param("mail")String mail);
	
	
	/**
	 * Recherche des utilisateurs à partir de critères
	 * 
	 * @param criteria critères de recherche des utilisateurs {@link CustomerCriteria}
	 * @return List of customers {@link Customer}
	 * @author Eric LEGBA
	 */
	List<Customer> findCustomersByCriteria(@Param("criteria")CustomerCriteria criteria);
	
	/**
	 * Vérifier si une adresse mail est déjà utilisé par un autre utilisateur.
	 * 
	 * @param mail adresse mail à vérifier {@link String}
	 * @return boolean
	 */
	
	boolean existsCustomerByMail(@Param("mail")String mail);
	
	
	/**
	 * Vérifier si un numéro de téléphone est déjà utilisé par un autre utilisateur.
	 * 
	 * @param phone numéro de téléphone à vérifier {@link String}
	 * @return boolean
	 */
	
	boolean existsCustomerByPhone(@Param("phone")String phone);
	
	
	/**
	 * Vérifier si un utilisateur ayant un nom et un prénom existe déjà.
	 * 
	 * @param name  nom de l'utilisateur {@link String}
	 * @param firstName prénom de l'utilisateur {@link String}
	 * @return boolean
	 */
	
	boolean existsCustomerByNameFirstName(@Param("name")String name, @Param("firstName")String firstName);
	
	

}
