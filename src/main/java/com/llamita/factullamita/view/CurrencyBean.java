package com.llamita.factullamita.view;

import java.io.Serializable;

public class CurrencyBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7767879640526832237L;
	
	private Integer id;
	private String country;
	private String symbol;
	private String denomination;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	
	
	
}
