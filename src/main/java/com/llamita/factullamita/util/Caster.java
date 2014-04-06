package com.llamita.factullamita.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.llamita.factullamita.model.Bill;
import com.llamita.factullamita.model.BillDetail;
import com.llamita.factullamita.model.Currency;
import com.llamita.factullamita.model.Customer;
import com.llamita.factullamita.view.BillBean;
import com.llamita.factullamita.view.BillDetailBean;
import com.llamita.factullamita.view.CurrencyBean;
import com.llamita.factullamita.view.CustomerBean;

public class Caster {
	
	private final static Logger log = Logger.getLogger(Caster.class);

	public static CustomerBean customerModelToBean(Customer model,CustomerBean bean){
		log.info("* Convertir Customer Modelo -> Bean [Inicio] *");
		if(bean!=null){   
			bean.setId(model.getId());
			bean.setName(model.getName());
			bean.setRuc(model.getName());
			bean.setAddress(model.getAddress());
		}
		log.info("* Convertir Customer Modelo -> Bean [Fin] *");
		return bean;
	}
	
	public static Customer customerBeanToModel(CustomerBean bean, Customer model){
		log.info("* Convertir Customer Bean -> Modelo [Inicio] *");
		if(model!=null){
			model.setId(bean.getId());
			model.setName(bean.getName());
			model.setRuc(bean.getRuc());
			model.setAddress(bean.getAddress());
		}
		log.info("* Convertir Customer Bean -> Modelo [Inicio] *");
		return model;
	}
	
	public static Customer customerBeanToModel(CustomerBean bean){
		log.info("* Convertir Customer Bean -> Modelo nuevo [Inicio] *");
		Customer model = new Customer();
		model.setName(bean.getName());
		model.setRuc(bean.getRuc());
		model.setAddress(bean.getAddress());
		log.info("* Convertir Customer Bean -> Modelo nuevo [Fin] *");
		return model;
	}
	
	public static CustomerBean customerModelToBean(Customer customer){
		log.info("* Convertir Customer Modelo -> Bean nuevo [Inicio] *");
		CustomerBean bean = new CustomerBean();
		bean.setId(customer.getId());
		bean.setName(customer.getName());
		bean.setRuc(customer.getRuc());
		bean.setAddress(customer.getAddress());
		log.info("* Convertir Customer Modelo -> Bean nuevo [Fin] *");
		return bean;
	}
	
	public static BillDetail billDetailBeanToModel(BillDetailBean bean){
		log.info("* Convertir BillDetail Bean -> Modelo [Inicio] *");
		BillDetail model = new BillDetail();
		if(bean.getId() != null)
			model.setId(bean.getId());	
		model.setNumber(bean.getNumber());
		model.setQuantity(bean.getQuantity());
		model.setUnitPrice(bean.getUnitPrice());
		model.setAmount(bean.getAmount());
		model.setDescription(bean.getDescription());
		log.info("* Convertir BillDetail Bean -> Modelo [Fin] *");
		return model;
	}
	
	
	
	public static BillDetailBean billDetailModelToBean(BillDetail model){
		log.info("* Convertir BillDetail Modelo -> Bean [Inicio] *");
		BillDetailBean bean = new BillDetailBean();
		bean.setId(model.getId());
		bean.setAmount(model.getAmount());
		bean.setDescription(model.getDescription());
		bean.setIdBill(model.getBill().getId());
		bean.setNumber(model.getNumber());
		bean.setQuantity(model.getQuantity());
		bean.setUnitPrice(model.getUnitPrice());
		log.info("* Convertir BillDetail Modelo -> Bean [Fin] *");
		return bean;
	}
	
	public static CurrencyBean currencyModelToBean(Currency model){
		log.info("* Convertir Currency Modelo -> Bean [Inicio] *");
		CurrencyBean bean = new CurrencyBean();
		bean.setId(model.getId());
		bean.setCountry(model.getCountry());
		bean.setDenomination(model.getDenomination());
		bean.setSymbol(model.getSymbol());
		log.info("* Convertir Currency Modelo -> Bean [Fin] *");
		return bean;
	}
	
	public static Bill billBeanToModel(BillBean bean){
		log.info("* Convertir Bill Bean -> Modelo [Inicio] *");
		Bill bill = new Bill();
		if(bean.getId() != null)
			bill.setId(bean.getId());		
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
		log.info("* Convertir Bill Bean -> Modelo [Fin] *");
		return bill;
	}
	
	public static BillBean billModelToBean(Bill model){
		log.info("* Convertir Bill Modelo -> Bean [Inicio] *");
		BillBean bill = new BillBean();
		bill.setId(model.getId());
		bill.setConditions(model.getConditions());
		bill.setExchangeRate(model.getExchangeRate());
		bill.setIdCurrency(model.getIdCurrency());
		bill.setIdCustomer(model.getIdCustomer());
		bill.setIgv(model.getIgv());
		bill.setIssueDate(model.getIssueDate());
		bill.setNumber(model.getNumber());
		bill.setReferralGuide(model.getReferralGuide());
		bill.setSon(model.getSon());
		bill.setSubtotal(model.getSubtotal());
		bill.setTotal(model.getTotal());
		List<BillDetailBean> list = new ArrayList<BillDetailBean>();
		
		for(BillDetail detail: model.getDetails()){
			log.debug("* Detalle : "+detail);
			list.add(Caster.billDetailModelToBean(detail));
		}
		
		bill.setDetails(list);
		log.info("* Convertir Bill Modelo -> Bean [Fin] *");
		return bill;
	}
	
}
