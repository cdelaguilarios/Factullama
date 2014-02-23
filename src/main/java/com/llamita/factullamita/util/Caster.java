package com.llamita.factullamita.util;

import com.llamita.factullamita.model.Customer;
import com.llamita.factullamita.view.CustomerBean;

public class Caster {

	public static CustomerBean customerModelToBean(Customer model,CustomerBean bean){
		if(bean!=null){
			bean.setId(model.getId());
			bean.setName(model.getName());
			bean.setRuc(model.getName());
			bean.setAddress(model.getAddress());
		}
		return bean;
	}
	
	public static Customer customerBeanToModel(CustomerBean bean, Customer model){
		if(model!=null){
			model.setId(bean.getId());
			model.setName(bean.getName());
			model.setRuc(bean.getRuc());
			model.setAddress(bean.getAddress());
		}
		return model;
	}
	
	public static Customer customerBeanToModel(CustomerBean bean){
		Customer model = new Customer();
		model.setName(bean.getName());
		model.setRuc(bean.getRuc());
		model.setAddress(bean.getAddress());
		return model;
	}
	
	public static CustomerBean customerModelToBean(Customer customer){
		CustomerBean bean = new CustomerBean();
		bean.setId(customer.getId());
		bean.setName(customer.getName());
		bean.setRuc(customer.getRuc());
		bean.setAddress(customer.getAddress());
		return bean;
	}
	
}
