package com.llamita.factullamita.util;

import com.llamita.factullamita.model.Bill;
import com.llamita.factullamita.model.BillDetail;
import com.llamita.factullamita.model.Currency;
import com.llamita.factullamita.model.Customer;
import com.llamita.factullamita.view.BillBean;
import com.llamita.factullamita.view.BillDetailBean;
import com.llamita.factullamita.view.CurrencyBean;
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
	
	public static BillDetail billDetailBeanToModel(BillDetailBean bean){
		BillDetail model = new BillDetail();
		model.setNumber(bean.getNumber());
		model.setQuantity(bean.getQuantity());
		model.setUnitPrice(bean.getUnitPrice());
		model.setAmount(bean.getAmount());
		model.setDescription(bean.getDescription());
		model.setIdBill(bean.getIdBill());
		return model;
	}
	
	public static CurrencyBean currencyModelToBean(Currency model){
		CurrencyBean bean = new CurrencyBean();
		bean.setId(model.getId());
		bean.setCountry(model.getCountry());
		bean.setDenomination(model.getDenomination());
		bean.setSymbol(model.getSymbol());
		return bean;
	}
	
	public static Bill billBeanToModel(BillBean bean){
		Bill bill = new Bill();
		bill.setReferralGuide(bean.getReferralGuide());
		bill.setConditions(bean.getConditions());
		bill.setExchangeRate(bean.getExchangeRate());
		bill.setSon(bean.getSon());
//		bill.setIssueDate(bean.getIssueDate());
		bill.setIdCurrency(bean.getIdCurrency());
		bill.setSubtotal(bean.getSubtotal());
		bill.setIgv(bean.getIgv());
		bill.setTotal(bean.getTotal());
		bill.setIdCustomer(bean.getIdCustomer());
		bill.setNumber(bean.getNumber());
		return bill;
	}
	
}
