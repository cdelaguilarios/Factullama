package com.llamita.factullamita.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DETALLE_FACTURA")
public class BillDetail implements Serializable{

	private static final long serialVersionUID = -6998318888890422765L;

	@Id
	@GeneratedValue
	@Column(name = "IN_IDDETALLE_FACTURA")
	private Integer id;
	
	@Column(name = "IN_NUMERO")
	private int number;
	
	@Column(name = "VC_DESCRIPCION")
	private String description;
	
	@Column(name = "IN_CANTIDAD")
	private int quantity;
	
	@Column(name = "VC_PRECIO_UNITARIO")
	private String unitPrice;
	
	@Column(name = "VC_IMPORTE")
	private String amount;
	
	@ManyToOne
	@JoinColumn(name="IN_IDFACTURA")
	private Bill bill;
	
    public BillDetail() {}

	public int getId() {
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
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	
	
}
