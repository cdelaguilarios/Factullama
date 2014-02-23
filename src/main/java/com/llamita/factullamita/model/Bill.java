package com.llamita.factullamita.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

@Entity
@Table( name = "FACTURA")
public class Bill implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6341632262507137398L;

	@Id
	@GeneratedValue
	@Column(name = "IN_IDFACTURA")
	private int id;
	
	@Column(name = "VC_NUMERO")
	private String number;
	
	@Column(name = "DT_FECHA_EMISION")
	private Date issue;
	
	@Column(name = "VC_GUIA_REMISION")
	private String wallbill;
	
	@Column(name = "VC_CONDICIONES")
	private String conditions;
	
	@Column(name = "VC_TIPO_CAMBIO")
	private String exchangeRate;
	
	@Column(name = "VC_SON")
	private String son;
	
	@Column(name = "CH_ESTADO")
	private String status;
	
	@Column(name = "DT_FECHA_CANCELADO")
	private String canceled;
	
	@Column(name = "VC_SUBTOTAL")
	private String subtotal;
	
	@Column(name = "VC_IGV")
	private String igv;
	
	@Column(name = "VC_TOTAL")
	private String total;
	
	@Column(name = "IN_IDCONFIGURACION")
	private int idConf;
	
	@Column(name = "IN_IDCLIENTE")
	private int idCustumer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getIssue() {
		return issue;
	}

	public void setIssue(Date issue) {
		this.issue = issue;
	}

	public String getWallbill() {
		return wallbill;
	}

	public void setWallbill(String wallbill) {
		this.wallbill = wallbill;
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

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
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

	public int getIdConf() {
		return idConf;
	}

	public void setIdConf(int idConf) {
		this.idConf = idConf;
	}

	public int getIdCustumer() {
		return idCustumer;
	}

	public void setIdCustumer(int idCustumer) {
		this.idCustumer = idCustumer;
	}
	
	
	
}
