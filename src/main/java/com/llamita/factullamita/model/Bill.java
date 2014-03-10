package com.llamita.factullamita.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FACTURA")
public class Bill implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6068768337925415452L;

	@Id
	@GeneratedValue
	@Column(name = "IN_IDFACTURA")
	private Integer id;
	
	@Column(name = "VC_NUMERO")
	private String number;
	
	@Column(name = "DT_FECHA_EMISION")
	private Date issueDate;
	
	@Column(name = "VC_GUIA_REMISION")
	private String referralGuide;
	
	@Column(name = "VC_CONDICIONES")
	private String conditions;
	
	@Column(name = "VC_TIPO_CAMBIO")
	private String exchangeRate;
	
	@Column(name = "VC_SON")
	private String son;
	
	@Column(name = "CH_ESTADO")
	private String status;
	
	@Column(name = "DT_FECHA_CANCELADO")
	private Date canceledDate;
	
	@Column(name = "VC_SUBTOTAL")
	private String subtotal;
	
	@Column(name = "VC_IGV")
	private String igv;
	
	@Column(name = "VC_TOTAL")
	private String total;
	
	@Column(name = "IN_IDMONEDA")
	private Integer idCurrency;
	
	@Column(name = "IN_IDCLIENTE")
	private Integer idCustomer;

    public Bill() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCanceledDate() {
		return canceledDate;
	}

	public void setCanceledDate(Date canceledDate) {
		this.canceledDate = canceledDate;
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

	public Integer getIdCurrency() {
		return idCurrency;
	}

	public void setIdCurrency(Integer idCurrency) {
		this.idCurrency = idCurrency;
	}

	public Integer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}

	
	
}
