package com.llamita.factullamita.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.llamita.factullamita.model.Customer;

@Repository
@Transactional
public class CustomerRepository extends HibernateRepository{

	public List<Customer> listCustomer(){
		Criteria criteria = getSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("state", "1"));
		return criteria.list();
	}
	
	public void addOrUpdateCustomer(Customer customer){
		getSession().saveOrUpdate(customer);
	}
	
	public void updateCustomer(Customer customer){
		getSession().update(customer);
	}
	
	public Customer getCustomer(Integer idCustomer){
		return (Customer) getSession().get(Customer.class, idCustomer);
	}
	
}
