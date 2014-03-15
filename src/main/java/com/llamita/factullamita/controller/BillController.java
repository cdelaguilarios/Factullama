package com.llamita.factullamita.controller;

import javax.validation.Valid;

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
	
	@RequestMapping(value="/admin/pdf",method=RequestMethod.GET)
	public ModelAndView generatePdf(@Valid @ModelAttribute(value="bill") BillBean bill,final BindingResult bindingResult, final ModelMap modelMap){
		if(bindingResult.hasErrors()){
			return new ModelAndView("/index");
		}
		
		ModelAndView m = new ModelAndView("GeneratePdf");
		m.getModelMap().addAttribute("bill", bill);
		return m;
	}	
}
