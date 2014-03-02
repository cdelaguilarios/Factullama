package com.llamita.factullamita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.llamita.factullamita.logic.ManageCurrencyLogic;
import com.llamita.factullamita.model.Currency;

@Controller
public class CurrencyController {

	
	@Autowired
	private ManageCurrencyLogic manageCurrencyLogic;
	
	@ModelAttribute("allCurrencies")
	public List<Currency> listCurrencies(){
		return manageCurrencyLogic.listCurrency();
	}
	
}
