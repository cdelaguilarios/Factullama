package com.llamita.factullamita.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.llamita.factullamita.logic.ManageCurrencyLogic;
import com.llamita.factullamita.logic.ManageCustomerLogic;
import com.llamita.factullamita.model.Customer;
import com.llamita.factullamita.util.Caster;
import com.llamita.factullamita.view.BillBean;
import com.llamita.factullamita.view.BillDetailBean;
import com.llamita.factullamita.view.CustomerBean;

@Controller
@RequestMapping(value="/admin")
public class CustomerController {
	
	@Autowired
	private ManageCustomerLogic manageCustomerLogic;
	
	@Autowired
	private ManageCurrencyLogic manageCurrencyLogic;
	
	@RequestMapping(value="/customer",method=RequestMethod.GET)
	public String listCustomers(ModelMap modelMap){
		List<Customer> customers = manageCustomerLogic.listCustomer();
        modelMap.addAttribute("customers", customers);
        
		return "/customer/list";
	}
	
	@RequestMapping(value="/newCustomer",method=RequestMethod.GET)
	public String newCustomerInit(ModelMap modelMap){
		CustomerBean customer = new CustomerBean();
		modelMap.addAttribute("customer",customer);
		return "/customer/new";
	}
	
	@RequestMapping(value="/newCustomer",method=RequestMethod.POST)
	public String newcustomer(@Valid @ModelAttribute(value="customer") CustomerBean customer,final BindingResult bindingResult, final ModelMap modelMap ){
		if(bindingResult.hasErrors()){
			return "/customer/new";
		}
		manageCustomerLogic.addCustomer(Caster.customerBeanToModel(customer));
		modelMap.clear();
		
		return listCustomers(modelMap);
	}
	
	@RequestMapping(value="/updCustomer/{idCustomer}",method=RequestMethod.GET)
	public String updCustomerInit(@PathVariable Integer idCustomer, ModelMap modelMap){
		CustomerBean customer = Caster.customerModelToBean(manageCustomerLogic.getCustomer(idCustomer));
		modelMap.addAttribute("customer",customer);
		return "/customer/upd";
	}
	
	@RequestMapping(value="/updCustomer",method=RequestMethod.POST)
	public String updcustomer(@Valid @ModelAttribute(value="customer") CustomerBean customer,final BindingResult bindingResult, final ModelMap modelMap ){
		if(bindingResult.hasErrors()){
			return "/customer/upd";
		}
		manageCustomerLogic.updCustomer(Caster.customerBeanToModel(customer,manageCustomerLogic.getCustomer(customer.getId())));
		modelMap.clear();
		
		return listCustomers(modelMap);
	}
	
	@RequestMapping(value="/addBill/{idCustomer}",method=RequestMethod.GET)
	public String addBillInit(@PathVariable Integer idCustomer, ModelMap modelMap){
		BillBean bill = new BillBean();
		bill.setIdCustomer(idCustomer);
		bill.setCurrencies(manageCurrencyLogic.listCurrency());
		modelMap.addAttribute("bill", bill);
		return "/bill/newHead";
	}

	@RequestMapping(value="/fillBillDetail",method=RequestMethod.POST)
	public String fillBillDetail(@Valid @ModelAttribute(value="bill") BillBean bill,final BindingResult bindingResult, ModelMap modelMap){
		if(bindingResult.hasErrors()){
			System.out.println("Error: "+bindingResult.getErrorCount());
			return "/addBill/"+bill.getIdCustomer();
		}
		modelMap.addAttribute("bill", bill);
		return "/bill/newBill";
	}
	
	@RequestMapping(value="/newDetail/{idBill}",method=RequestMethod.GET)
	public String addDetailBillInit(@PathVariable Integer idBill, ModelMap modelMap){
		BillDetailBean billDetail = new BillDetailBean();
		billDetail.setIdBill(idBill);
		modelMap.addAttribute("billDetail", billDetail);
		return "/bill/newDetail";
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String addDetailBill(@Valid @ModelAttribute(value="billDetail") BillDetailBean billDetail, final BindingResult bindingResult, ModelMap modelMap){
		if(bindingResult.hasErrors()){
			return "/bill/newDetail";
		}
		return "/bill/newBill";
	}
	

}
