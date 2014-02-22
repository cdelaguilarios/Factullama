package com.llamita.factullamita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name ="DETALLE_FACTURA")
public class BillDetail {
	
	@Column( name = "IN_IDDETALLE_FACTURA")
	private int id;
	
	@Column( name = "IN_NUMERO")
	private int number;
	
	@Column( name = "VC_DESCRIPCION")
	private String description;
	
	@Column( name = "IN_CANT")
	private int quantity;
	
	@Column( name = "VC_PRECIO_UNITARIO")
	private int unitPrice;
	
	@Column( name = "VC_IMPORTE")
	private String amount;
	
	@Column( name = "IN_IDFACTURA")
	private int idBill;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the unitPrice
	 */
	public int getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the idBill
	 */
	public int getIdBill() {
		return idBill;
	}

	/**
	 * @param idBill the idBill to set
	 */
	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}
}
