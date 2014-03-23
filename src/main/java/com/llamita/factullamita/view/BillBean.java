package com.llamita.factullamita.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.llamita.factullamita.model.Customer;
import com.llamita.factullamita.util.Formatter;


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
	
	private List<BillDetailBean> details = new ArrayList<BillDetailBean>();
	private CustomerBean customer;
	private CurrencyBean currency;
	
	@NotNull
	private Integer idCustomer;
	
	@NotNull
	@Size(min=1,max=20,message="El número de la factura debe ser colocado.")
	@Pattern(regexp="^[0-9]*$",message="El número de la factura debe contener sólo números.")
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
		return Formatter.amount(calculateSubTotal());
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getIgv() {
		return Formatter.amount(calculateIgv());
	}
	public void setIgv(String igv) {
		this.igv = igv;
	}
	public String getTotal() {
		return Formatter.amount(calculateTotal());
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
	public CustomerBean getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}
	public CurrencyBean getCurrency() {
		return currency;
	}
	public void setCurrency(CurrencyBean currency) {
		this.currency = currency;
	}
	
	/*
	 * Operaciones de calculo automatico
	 * 
	 * */
	
	
	public double calculateSubTotal(){
		double subTotal = 0;
		if(details!=null && details.size()>0){
			for(BillDetailBean detail : details){
				subTotal += Double.parseDouble(detail.getAmount());
			}
		}
		return subTotal;
	}
	
	public double calculateIgv(){
		return calculateSubTotal()*0.18;
	}
	
	public double calculateTotal(){
		return calculateSubTotal()+calculateIgv();
	}

}
