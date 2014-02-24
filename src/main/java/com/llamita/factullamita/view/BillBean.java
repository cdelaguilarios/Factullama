package com.llamita.factullamita.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.llamita.factullamita.model.BillDetail;
import com.llamita.factullamita.model.Currency;

public class BillBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5058174990114246043L;
	
	private int idCustomer;
	private String number;
	private String referralGuide;
	private String conditions;
	private String exchangeRate;
	private String son;
	private Date issueDate;
	private int idCurrency;
	private String subtotal;
	private String igv;
	private String total;
	
	private List<Currency> currencies; 
	private List<BillDetail> details;
	
	public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getReferralGuide() {
		return referralGuide;
	}
	public void setReferralGuide(String referralGuide) {
		this.referralGuide = referralGuide;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getSon() {
		return son;
	}
	public void setSon(String son) {
		this.son = son;
	}
	public int getIdCurrency() {
		return idCurrency;
	}
	public void setIdCurrency(int idCurrency) {
		this.idCurrency = idCurrency;
	}
	public List<Currency> getCurrencies() {
		return currencies;
	}
	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public List<BillDetail> getDetails() {
		return details;
	}
	public void setDetails(List<BillDetail> details) {
		this.details = details;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getIgv() {
		return igv;
	}
	public void setIgv(String igv) {
		this.igv = igv;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	
	
	
}
