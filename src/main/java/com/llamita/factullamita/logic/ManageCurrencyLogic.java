package com.llamita.factullamita.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llamita.factullamita.model.Currency;
import com.llamita.factullamita.repository.CurrencyRepository;

@Service
public class ManageCurrencyLogic {

	@Autowired
	private CurrencyRepository currencyRepostory;
	
	public List<Currency> listCurrency(){
		return currencyRepostory.listCurrency();
	}
	
	public Currency getCurrency(Integer id){
		return currencyRepostory.getCurrency(id);
	}
	
}
