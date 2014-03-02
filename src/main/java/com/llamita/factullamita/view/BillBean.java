package com.llamita.factullamita.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.llamita.factullamita.model.Currency;

public class BillBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5058174990114246043L;

	private Integer id;
	
	private String referralGuide;
	private String conditions;
	private String exchangeRate;
	private String son;
	private Date issueDate;
	private Integer idCurrency;
	private String subtotal;
	private String igv;
	private String total;
	
	private List<Currency> currencies; 
	private List<BillDetailBean> details;

	@NotNull
	private Integer idCustomer;
	
	@NotNull
	@Size(min=1,max=20)
	private String number;

	public Integer getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Integer idCustomer) {
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
	public Integer getIdCurrency() {
		return idCurrency;
	}
	public void setIdCurrency(Integer idCurrency) {
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
	public List<BillDetailBean> getDetails() {
		return details;
	}
	public void setDetails(List<BillDetailBean> details) {
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
