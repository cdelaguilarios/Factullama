package com.llamita.factullamita.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.llamita.factullamita.logic.ManageBillLogic;
import com.llamita.factullamita.logic.ManageCurrencyLogic;
import com.llamita.factullamita.logic.ManageCustomerLogic;
import com.llamita.factullamita.model.Bill;
import com.llamita.factullamita.model.Currency;
import com.llamita.factullamita.model.Customer;
import com.llamita.factullamita.util.Caster;
import com.llamita.factullamita.view.BillBean;
import com.llamita.factullamita.view.BillDetailBean;
import com.llamita.factullamita.view.CustomerBean;

@Controller
@RequestMapping(value="/admin")
public class CustomerController {
	
	private Logger log = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private ManageCustomerLogic manageCustomerLogic;

	@Autowired
	private ManageCurrencyLogic manageCurrencyLogic;
	
	@Autowired
	private ManageBillLogic manageBillLogic;
	
	@ModelAttribute("allCurrencies")
	public List<Currency> listCurrencies(){
		return manageCurrencyLogic.listCurrency();
	}

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

	/**
	 * Controladores de Procesamiento de Facturas
	 *  **/
	
	@RequestMapping(value="/addBill/{idCustomer}",method=RequestMethod.GET)
	public String addBillInit(@PathVariable Integer idCustomer, ModelMap modelMap){
		log.info("* Controlador: Customer - Iniciar formulario cabecera de factura [Inicio] *");
		BillBean bill = new BillBean();
		bill.setIdCustomer(idCustomer);
		modelMap.addAttribute("bill", bill);
		log.info("* Controlador: Customer - Iniciar formulario cabecera de factura [Fin] *");
		return "/bill/newHead";
	}

	@RequestMapping(value="/fillBillDetail",method=RequestMethod.POST)
	public String fillBillDetail(@Valid @ModelAttribute(value="bill") BillBean bill,final BindingResult bindingResult, ModelMap modelMap){
		log.info("* Controlador: Customer - Iniciar formulario detalle de factura [Inicio] *");
		if(bindingResult.hasErrors()){
			return "/bill/newHead";
		}

		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		modelMap.addAttribute("bill", bill);
		log.info("* Controlador: Customer - Iniciar formulario detalle de factura [Fin] *");
		return "/bill/newBill";
	}
	
//	@RequestMapping(value="/viewBill/{idBill}",method=RequestMethod.GET)
//	public String viewBillInit(@PathVariable Integer idBill, ModelMap modelMap){
//		log.info("* Controlador: Customer - Ver factura [Inicio] *");
//		BillBean bill = Caster.billModelToBean(manageBillLogic.getBill(idBill));
//		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
//		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
//		modelMap.addAttribute("bill", bill);
//		log.info("* Controlador: Customer - Ver factura [Fin] *");
//		return "/bill/viewBill";
//	}

	@RequestMapping(value="/bill",method=RequestMethod.GET)
	public String listBills(ModelMap modelMap){
		log.info("* Controlador: Customer - Listar facturas [Inicio] *");
		List<Bill> bills = manageBillLogic.listBill();
        modelMap.addAttribute("bills", bills);
        log.info("* Controlador: Customer - Listar facturas [Fin] *");
		return "/bill/list";
	}
	
	@RequestMapping(value="/billProcess", params={"addDetail"})
    public String addDetail(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult) {
		log.info("* Controlador: Customer - Agregar detalle [Inicio] *");
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		bill.getDetails().add(new BillDetailBean());
		log.info("* Controlador: Customer - Agregar detalle [Fin] *");
        return "/bill/newBill";
    }
	
	@RequestMapping(value="/billProcess", params={"removeDetail"})
    public String removeDetail(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult, final HttpServletRequest req) {
		log.info("* Controlador: Customer - Remover detalle [Inicio] *");
		final Integer rowId = Integer.valueOf(req.getParameter("removeDetail"));
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		bill.getDetails().remove(rowId.intValue());
		log.info("* Controlador: Customer - Remover detalle [Fin] *");
        return "/bill/newBill";
    }
	
	@RequestMapping(value="/billProcess", params={"recalculate"})
    public String recalculateBill(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResultq) {
		log.info("* Controlador: Customer - Recalcular detalle [Inicio] *");
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		log.info("* Controlador: Customer - Recalcular detalle [Fin] *");
        return "/bill/newBill";
    }
	
	@RequestMapping(value="/billProcess", params={"save"})
    public String saveBill(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult, ModelMap modelMap) {
		log.info("* Controlador: Customer - Guardar factura [Inicio] *");
		if(bill!=null){
			System.out.println("* Guardar Cabecera *");
			//Guardar Cabecera
			manageBillLogic.addBill(Caster.billBeanToModel(bill));
			//GuardarDetalle
			System.out.println("* Guardar Detalle *");
			for(BillDetailBean detail:bill.getDetails()){
				detail.setIdBill(manageBillLogic.getBillByNumber(bill.getNumber()).getId());
				manageBillLogic.addBillDetail(Caster.billDetailBeanToModel(detail));
			}
			
			modelMap.clear();
		}
		log.info("* Controlador: Customer - Guardar factura [Inicio] *");
        return listBills(modelMap);
    }
	
	@RequestMapping(value="/billProcess", params={"print"})
    public ModelAndView printBill(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult) {
		log.info("* Controlador: Customer - Imprimir Factura [Inicio] *");
//		if(bindingResult.hasErrors()){
//			System.out.println("Errores : "+bindingResult.getFieldError().getField()+" "+bindingResult.getFieldError().getCode());
//			return new ModelAndView("/customer/list");
//		}
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		bill.setIssueDate(new Date());
		ModelAndView m = new ModelAndView("GeneratePdf");
		m.getModelMap().addAttribute("bill", bill);
		log.info("* Controlador: Customer - Imprimir Factura [Fin] *");
		return m;
    }
	
	@RequestMapping(value="/viewBill/{idBill}",method=RequestMethod.GET)
	public ModelAndView viewBillInit(@PathVariable Integer idBill, ModelMap modelMap){
		log.info("* Controlador: Customer - Ver factura [Inicio] *");
		BillBean bill = Caster.billModelToBean(manageBillLogic.getBill(idBill));
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		ModelAndView m = new ModelAndView("GeneratePdf");
		m.getModelMap().addAttribute("bill", bill);
		log.info("* Controlador: Customer - Ver factura [Fin] *");
		return m;
	}

}
