package com.llamita.factullamita.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llamita.factullamita.model.Customer;
import com.llamita.factullamita.repository.CustomerRepository;

@Service
public class ManageCustomerLogic {

	@Autowired
	private CustomerRepository customerRepository;
	
	public void addCustomer(Customer customer){
		customerRepository.addOrUpdateCustomer(customer);
	}
	
	public void updCustomer(Customer customer){
		customerRepository.addOrUpdateCustomer(customer);
	}
	
	public List<Customer> listCustomer(){
		return customerRepository.listCustomer();
	}
	
	public Customer getCustomer(Integer id){
		return customerRepository.getCustomer(id);
	}
	
}
