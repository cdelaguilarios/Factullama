package com.llamita.factullamita.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.llamita.factullamita.logic.ManageBillDetailLogic;
import com.llamita.factullamita.logic.ManageBillLogic;
import com.llamita.factullamita.logic.ManageCurrencyLogic;
import com.llamita.factullamita.logic.ManageCustomerLogic;
import com.llamita.factullamita.model.Bill;
import com.llamita.factullamita.model.Currency;
import com.llamita.factullamita.util.Caster;
import com.llamita.factullamita.view.BillBean;
import com.llamita.factullamita.view.BillDetailBean;

@Controller
@RequestMapping(value="/admin")
public class BillController {
	
	private Logger log = Logger.getLogger(BillController.class);
	
	@Autowired
	private ManageCustomerLogic manageCustomerLogic;
	
	@Autowired
	private ManageCurrencyLogic manageCurrencyLogic;
	
	@Autowired
	private ManageBillLogic manageBillLogic;
	
	@Autowired
	private ManageBillDetailLogic manageBillDetailLogic;
	
	@ModelAttribute("allCurrencies")
	public List<Currency> listCurrencies(){
		return manageCurrencyLogic.listCurrency();
	}
	
	/**
	 * Controladores de Procesamiento de Facturas
	 *  **/
	@RequestMapping(value="/bill",method=RequestMethod.GET)
	public String listBills(ModelMap modelMap){
		log.info("* Controlador: Customer - Listar facturas [Inicio] *");
		List<Bill> bills = manageBillLogic.listBill();
		List<BillBean> billsBeans = new ArrayList<BillBean>();
		for(Bill bill : bills) {
            log.info("Una iteracion mas....");
			billsBeans.add(Caster.billModelToBean(bill));
        }
        modelMap.addAttribute("bills", billsBeans);
        log.info("* Controlador: Customer - Listar facturas [Fin] *");
		return "/bill/list";
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
	
	@RequestMapping(value="/addBill/{idCustomer}",method=RequestMethod.GET)
	public String addBillInit(@PathVariable Integer idCustomer, ModelMap modelMap){
		log.info("* Controlador: Customer - Iniciar formulario cabecera de factura [Inicio] *");
		BillBean bill = new BillBean();
		bill.setIdCustomer(idCustomer);
		modelMap.addAttribute("bill", bill);
		log.info("* Controlador: Customer - Iniciar formulario cabecera de factura [Fin] *");
		return "/bill/newHead";
	}
	
	@RequestMapping(value="/billProcess", params={"save"})
    public String saveBill(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult, ModelMap modelMap) {
		log.info("* Controlador: Customer - Guardar factura [Inicio] *");
		if(bill!=null){
			log.debug("* Guardar Cabecera *");
			//Guardar Cabecera
			Bill billModel = Caster.billBeanToModel(bill);
			manageBillLogic.addBill(billModel);
			log.debug("* IdBill: "+billModel.getId());
			//GuardarDetalle
			log.debug("* Guardar Detalle *");
			for(BillDetailBean detail:bill.getDetails()){
				detail.setIdBill(billModel.getId());
				manageBillLogic.addBillDetail(Caster.billDetailBeanToModel(detail), billModel);
			}
			
			modelMap.clear();
		}
		log.info("* Controlador: Customer - Guardar factura [Inicio] *");
        return listBills(modelMap);
    }
	
	@RequestMapping(value="/billProcess", params={"print"})
    public ModelAndView printBill(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult) {
		log.info("* Controlador: Customer - Imprimir Factura [Inicio] *");
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		bill.setIssueDate(new Date());
		ModelAndView m = new ModelAndView("GeneratePdf");
		m.getModelMap().addAttribute("bill", bill);
		log.info("* Controlador: Customer - Imprimir Factura [Fin] *");
		return m;
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
	
	@RequestMapping(value="/delBill/{idBill}",method=RequestMethod.GET)
	public String delBillInit(@PathVariable Integer idBill, ModelMap modelMap){
		log.info("* Controlador: Customer - Eliminar bill [Inicio] *");
		manageBillLogic.delBill(idBill);
		log.info("* Controlador: Customer - Eliminar bill [Fin] *");
		return listBills(modelMap);
	}
	
	@RequestMapping(value="/updBill/{idBill}",method=RequestMethod.GET)
	public String updBillInit(@PathVariable Integer idBill, ModelMap modelMap){
		log.info("* Controlador: Bill - Actualizar factura [Inicio] *");
		BillBean bill = Caster.billModelToBean(manageBillLogic.getBill(idBill));
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		modelMap.addAttribute("bill",bill);
		modelMap.addAttribute("currency",bill.getCurrency());
		modelMap.addAttribute("customer",bill.getCustomer());
		log.info("* Controlador: Bill - Actualizar factura [Fin] *");
		return "/bill/updBill";
	}
	
	@RequestMapping(value="/updBill",params={"save"})
	public String updBill(@Valid @ModelAttribute(value="bill") BillBean bill,final BindingResult bindingResult, final ModelMap modelMap ){
		log.info("* Controlador: Bill - Actualizar factura [Inicio] *");
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		modelMap.addAttribute("bill",bill);
		modelMap.addAttribute("customer",bill.getCustomer());
		modelMap.addAttribute("currency",bill.getCurrency());
		
		if(bindingResult.hasErrors()){			
			log.info("El total de errores es: " + bindingResult.getFieldErrorCount());
			for(FieldError error : bindingResult.getFieldErrors()) {
				log.info("Campo: " + error.getField() + "Mensaje: " + error.getDefaultMessage());
			}
			return "/bill/updBill";
		}	
		
		manageBillLogic.updBill(Caster.billBeanToModel(bill));
		for (BillDetailBean detail : bill.getDetails()) {
			detail.setIdBill(bill.getId());
			manageBillLogic.addBillDetail(Caster.billDetailBeanToModel(detail), null);
		}	
		modelMap.clear();
		log.info("* Controlador: Bill - Actualizar factura [Fin] *");
		return "redirect:/admin/updBill/"+bill.getId();	        
	}
	
	@RequestMapping(value="/updBill", params={"addDetail"})
    public String addUpdDetail(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult, final ModelMap modelMap) {
		log.info("* Controlador: Customer - Agregar detalle en actualizar [Inicio] *");
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		bill.getDetails().add(new BillDetailBean());
		modelMap.addAttribute("bill",bill);
		modelMap.addAttribute("customer",bill.getCustomer());
		modelMap.addAttribute("currency",bill.getCurrency());
		log.info("* Controlador: Customer - Agregar detalle en actualizar [Fin] *");
        return "/bill/updBill";
    }
	
	@RequestMapping(value="/updBill", params={"removeDetail"})
    public String removeUpdDetail(@ModelAttribute(value="bill") final BillBean bill, final BindingResult bindingResult, final HttpServletRequest req, final ModelMap modelMap) {
		log.info("* Controlador: Customer - Remover detalle en actualizar [Inicio] *");
		final Integer rowId = Integer.valueOf(req.getParameter("removeDetail"));
		bill.setCustomer(Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer())));
		bill.setCurrency(Caster.currencyModelToBean(manageCurrencyLogic.getCurrency(bill.getIdCurrency())));
		
		manageBillDetailLogic.delBillDetail((bill.getDetails().get(rowId.intValue()).getId()));
		bill.getDetails().remove(rowId.intValue());
		
		modelMap.addAttribute("bill",bill);
		modelMap.addAttribute("customer",bill.getCustomer());
		modelMap.addAttribute("currency",bill.getCurrency());
		log.info("* Controlador: Customer - Remover detalle en actualizar [Fin] *");
        return "/bill/updBill";
    }
		
	@RequestMapping(value="/pdf",method=RequestMethod.GET)
	public ModelAndView generatePdf(@Valid @ModelAttribute(value="bill") BillBean bill,final BindingResult bindingResult, final ModelMap modelMap){
		log.info("* Controlador: Bill - GenerarPDF [Inicio] *");
		if(bindingResult.hasErrors()){
			return new ModelAndView("/index");
		}
		
		ModelAndView m = new ModelAndView("GeneratePdf");
		m.getModelMap().addAttribute("bill", bill);
		log.info("* Controlador: Bill - GenerarPDF [Fin] *");
		return m;
	}	
}
