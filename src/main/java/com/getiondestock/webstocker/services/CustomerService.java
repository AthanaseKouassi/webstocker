/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.Customer;
import java.util.List;

/**
 *
 * @author Athanase
 */

public interface CustomerService {
     	  
    public Customer getOne(Long id)  ;    
    public List<Customer> getAll() ;    
    public Customer creer(Customer t)  ;    
    public Customer update(Customer t) ;    
    public void delete(Long id) ;    
    List<Customer> findByLastName(String lastName);    
    List<Customer> findByFirstName(String lastName);
	
}
