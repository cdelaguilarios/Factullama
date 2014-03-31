package com.llamita.factullamita.view;

import java.io.Serializable;

import com.llamita.factullamita.util.Formatter;

public class BillDetailBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4043270582493595347L;
	
	
	private Integer id;
	private int number;
	private String description;
	private int quantity;
	private String unitPrice;
	private String amount;
	
	private int idBill;
	

	public BillDetailBean(){
		unitPrice = "0.00";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getAmount() {
		return Formatter.amount(calculateAmount());
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getIdBill() {
		return idBill;
	}
	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}
	
	public double calculateAmount(){
		double amountCal = 0;
		if(unitPrice!=null && unitPrice.trim().length()>0 && unitPrice.contains(".")){
			double price = Double.parseDouble(unitPrice.trim());
			amountCal = price * quantity;
		}
		return amountCal;
	}
	
}
