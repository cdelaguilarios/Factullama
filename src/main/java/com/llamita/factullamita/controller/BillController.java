package com.llamita.factullamita.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.llamita.factullamita.view.BillBean;

@Controller
public class BillController {
	
	private Logger log = Logger.getLogger(BillController.class);
	
	@RequestMapping(value="/admin/pdf",method=RequestMethod.GET)
	public ModelAndView generatePdf(@Valid @ModelAttribute(value="bill") BillBean bill,final BindingResult bindingResult, final ModelMap modelMap){
		log.info("* Controlador: Bill - GenerarPDF [Inicio] *");
		if(bindingResult.hasErrors()){
			return new ModelAndView("/customer/list");
		}
		
		ModelAndView m = new ModelAndView("GeneratePdf");
		m.getModelMap().addAttribute("bill", bill);
		log.info("* Controlador: Bill - GenerarPDF [Fin] *");
		return m;
	}	
}
