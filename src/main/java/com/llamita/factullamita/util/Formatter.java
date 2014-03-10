package com.llamita.factullamita.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Formatter {

	public static String amount(double amount){
		String newAmount = "";
		NumberFormat formatter = new DecimalFormat("#0.00");
		newAmount = formatter.format(amount).replace(',', '.');
		return newAmount;
	}
	
}
