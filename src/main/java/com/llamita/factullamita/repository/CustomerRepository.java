package com.llamita.factullamita.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.llamita.factullamita.model.Customer;

@Repository
public class CustomerRepository extends HibernateRepository{

	public List<Customer> listCustomer(){
		return null;
	}
	
}
