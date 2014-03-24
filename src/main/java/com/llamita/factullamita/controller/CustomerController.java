package com.llamita.factullamita.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.llamita.factullamita.logic.ManageCustomerLogic;
import com.llamita.factullamita.model.Customer;
import com.llamita.factullamita.util.Caster;
import com.llamita.factullamita.view.CustomerBean;

@Controller
@RequestMapping(value="/admin")
public class CustomerController {
	
	private Logger log = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private ManageCustomerLogic manageCustomerLogic;
		
	/**
	 * Controladores de Procesamiento de Clientes
	 *  **/
	
	@RequestMapping(value="/customer",method=RequestMethod.GET)
	public String listCustomers(ModelMap modelMap){
		log.info("* Controlador: Customer - Listar Clientes [Inicio] *");
		List<Customer> customers = manageCustomerLogic.listCustomer();
        modelMap.addAttribute("customers", customers);
        log.info("* Controlador: Customer - Listar Clientes [Fin] *");
		return "/customer/list";
	}
	
	@RequestMapping(value="/newCustomer",method=RequestMethod.GET)
	public String newCustomerInit(ModelMap modelMap){
		log.info("* Controlador: Customer - Iniciar formulario cliente [Inicio] *");
		CustomerBean customer = new CustomerBean();
		modelMap.addAttribute("customer",customer);
		log.info("* Controlador: Customer - Iniciar formulario cliente [Fin] *");
		return "/customer/new";
	}
	
	@RequestMapping(value="/newCustomer",method=RequestMethod.POST)
	public String newcustomer(@Valid @ModelAttribute(value="customer") CustomerBean customer,final BindingResult bindingResult, final ModelMap modelMap ){
		log.info("* Controlador: Customer - Registrar cliente [Inicio] *");
		if(bindingResult.hasErrors()){
			return "/customer/new";
		}
		manageCustomerLogic.addCustomer(Caster.customerBeanToModel(customer));
		modelMap.clear();
		log.info("* Controlador: Customer - Registrar cliente [Fin] *");
		return listCustomers(modelMap);
	}
	
	@RequestMapping(value="/updCustomer/{idCustomer}",method=RequestMethod.GET)
	public String updCustomerInit(@PathVariable Integer idCustomer, ModelMap modelMap){
		log.info("* Controlador: Customer - Iniciar formulario de actualización de cliente [Inicio] *");
		CustomerBean customer = Caster.customerModelToBean(manageCustomerLogic.getCustomer(idCustomer));
		modelMap.addAttribute("customer",customer);
		log.info("* Controlador: Customer - Iniciar formulario de actualización de cliente [Fin] *");
		return "/customer/upd";
	}
	
	@RequestMapping(value="/updCustomer",method=RequestMethod.POST)
	public String updcustomer(@Valid @ModelAttribute(value="customer") CustomerBean customer,final BindingResult bindingResult, final ModelMap modelMap ){
		log.info("* Controlador: Customer - Actualizar cliente [Inicio] *");
		if(bindingResult.hasErrors()){
			return "/customer/upd";
		}
		manageCustomerLogic.updCustomer(Caster.customerBeanToModel(customer,manageCustomerLogic.getCustomer(customer.getId())));
		modelMap.clear();
		log.info("* Controlador: Customer - Actualizar cliente [Fin] *");
		return listCustomers(modelMap);
	}	
	
	@RequestMapping(value="/delCustomer/{idCustomer}",method=RequestMethod.GET)
	public String delCustomerInit(@PathVariable Integer idCustomer, ModelMap modelMap){
		log.info("* Controlador: Customer - Eliminar customer [Inicio] *");
		manageCustomerLogic.delCustomer(idCustomer);
		log.info("* Controlador: Customer - Eliminar customer [Fin] *");
		return listCustomers(modelMap);
	}
}
