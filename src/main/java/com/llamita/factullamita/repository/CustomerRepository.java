package com.llamita.factullamita.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.llamita.factullamita.model.Customer;

@Repository
@Transactional
public class CustomerRepository extends HibernateRepository{

	public List<Customer> listCustomer(){
		Criteria criteria = getSession().createCriteria(Customer.class);
		return criteria.list();
	}
	
	public void addOrUpdateCustomer(Customer customer){
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
