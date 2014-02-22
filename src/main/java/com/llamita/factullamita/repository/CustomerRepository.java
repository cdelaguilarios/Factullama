package com.llamita.factullamita.repository;

import java.util.List;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.llamita.factullamita.model.Customer;

@Repository
public class CustomerRepository extends HibernateRepository{

	public List<Customer> listCustomer(){
		return null;
	}
	
	public void addCustomer(Customer customer){
		Transaction tx = null;
		try{
			tx = (Transaction) getSession().beginTransaction();
			getSession().saveOrUpdate(customer);
			tx.commit();
		}catch(Exception e){
			try{
    			tx.rollback();
    		}catch(RuntimeException rbe){
    			System.out.println("Couldn’t roll back transaction "+ rbe);
    		}
		}
	}
	
}
