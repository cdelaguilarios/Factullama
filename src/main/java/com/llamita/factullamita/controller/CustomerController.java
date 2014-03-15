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

	@RequestMapping(value="/customer",method=RequestMethod.GET)
	public String listCustomers(ModelMap modelMap){
		log.info("* Recuperando Clientes ...");
		List<Customer> customers = manageCustomerLogic.listCustomer();
        modelMap.addAttribute("customers", customers);
        
		return "/customer/list";
	}
	
	@RequestMapping(value="/newCustomer",method=RequestMethod.GET)
	public String newCustomerInit(ModelMap modelMap){
		log.info("* Nuevo Cliente - Iniciar formulario ...");
		CustomerBean customer = new CustomerBean();
		modelMap.addAttribute("customer",customer);
		return "/customer/new";
	}
	
	@RequestMapping(value="/newCustomer",method=RequestMethod.POST)
	public String newcustomer(@Valid @ModelAttribute(value="customer") CustomerBean customer,final BindingResult bindingResult, final ModelMap modelMap ){
		log.info("* Nuevo Cliente - registrar ...");
		if(bindingResult.hasErrors()){
			return "/customer/new";
		}
		manageCustomerLogic.addCustomer(Caster.customerBeanToModel(customer));
		modelMap.clear();
		
		return listCustomers(modelMap);
	}
	
	@RequestMapping(value="/updCustomer/{idCustomer}",method=RequestMethod.GET)
	public String updCustomerInit(@PathVariable Integer idCustomer, ModelMap modelMap){
		log.info("* Actualizar Cliente - Iniciar formulario ...");
		CustomerBean customer = Caster.customerModelToBean(manageCustomerLogic.getCustomer(idCustomer));
		modelMap.addAttribute("customer",customer);
		return "/customer/upd";
	}
	
	@RequestMapping(value="/updCustomer",method=RequestMethod.POST)
	public String updcustomer(@Valid @ModelAttribute(value="customer") CustomerBean customer,final BindingResult bindingResult, final ModelMap modelMap ){
		log.info("* Actualizar Cliente - registrar ...");
		if(bindingResult.hasErrors()){
			return "/customer/upd";
		}
		manageCustomerLogic.updCustomer(Caster.customerBeanToModel(customer,manageCustomerLogic.getCustomer(customer.getId())));
		modelMap.clear();
		
		return listCustomers(modelMap);
	}
	
	@RequestMapping(value="/addBill/{idCustomer}",method=RequestMethod.GET)
	public String addBillInit(@PathVariable Integer idCustomer, ModelMap modelMap){
		log.info("* Generar Factura - Iniciar formulario ...");
		BillBean bill = new BillBean();
		bill.setIdCustomer(idCustomer);
		modelMap.addAttribute("bill", bill);
		return "/bill/newHead";
	}

	@RequestMapping(value="/fillBillDetail",method=RequestMethod.POST)
	public String fillBillDetail(@Valid @ModelAttribute(value="bill") BillBean bill,final BindingResult bindingResult, ModelMap modelMap){
		log.info("* Generar Factura - Cabecera ...");
		if(bindingResult.hasErrors()){
			return "/bill/newHead";
		}

		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		modelMap.addAttribute("bill", bill);
		return "/bill/newBill";
	}
	
//	@RequestMapping(value="/newDetail/{idBill}",method=RequestMethod.GET)
//	public String addDetailBillInit(@PathVariable Integer idBill, ModelMap modelMap){
//		BillDetailBean billDetail = new BillDetailBean();
//		billDetail.setIdBill(idBill);
//		modelMap.addAttribute("billDetail", billDetail);
//		return "/bill/newDetail";
//	}
//	
//	@RequestMapping(value="/addDetail",method=RequestMethod.POST)
//	public String addDetailBill(@Valid @ModelAttribute(value="billDetail") BillDetailBean billDetail, final BindingResult bindingResult, ModelMap modelMap){
//		if(bindingResult.hasErrors()){
//			return "/bill/newDetail";
//		}
//		return "/bill/newBill";
//	}
//	
//	@RequestMapping(value="/backToHead",method=RequestMethod.GET)
//	public String backToHead(ModelMap modelMap){
//		
//		return "/bill/newHead";
//	}
	
	/**
	 * Controladores de Procesamiento de Factura
	 *  **/
	
	@RequestMapping(value="/bill",method=RequestMethod.GET)
	public String listBills(ModelMap modelMap){
		List<Bill> bills = manageBillLogic.listBill();
        modelMap.addAttribute("bills", bills);
        
		return "/bill/list";
	}
	
	@RequestMapping(value="/billProcess", params={"addDetail"})
    public String addDetail(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult) {
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		bill.getDetails().add(new BillDetailBean());
        return "/bill/newBill";
    }
	
	@RequestMapping(value="/billProcess", params={"removeDetail"})
    public String removeDetail(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult, final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeDetail"));
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		bill.getDetails().remove(rowId.intValue());
        return "/bill/newBill";
    }
	
	@RequestMapping(value="/billProcess", params={"recalculate"})
    public String recalculateBill(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResultq) {
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
        return "/bill/newBill";
    }
	
	@RequestMapping(value="/billProcess", params={"save"})
    public String saveBill(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult, ModelMap modelMap) {
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
        return listBills(modelMap);
    }
	
	@RequestMapping(value="/billProcess", params={"print"})
    public ModelAndView printBill(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult) {
//		if(bindingResult.hasErrors()){
//			System.out.println("Errores : "+bindingResult.getFieldError().getField()+" "+bindingResult.getFieldError().getCode());
//			return new ModelAndView("/customer/list");
//		}
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		bill.setIssueDate(new Date());
		ModelAndView m = new ModelAndView("GeneratePdf");
		m.getModelMap().addAttribute("bill", bill);
		return m;
    }

}
