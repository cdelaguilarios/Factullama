package com.llamita.factullamita.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

@Entity
@Table( name = "FACTURA")
public class Bill {
	
	@Column(name = "IN_IDFACTURA")
	private int id;
	
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
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the issue
	 */
	public Date getIssue() {
		return issue;
	}

	/**
	 * @param issue the issue to set
	 */
	public void setIssue(Date issue) {
		this.issue = issue;
	}

	/**
	 * @return the wallbill
	 */
	public String getWallbill() {
		return wallbill;
	}

	/**
	 * @param wallbill the wallbill to set
	 */
	public void setWallbill(String wallbill) {
		this.wallbill = wallbill;
	}

	/**
	 * @return the conditions
	 */
	public String getConditions() {
		return conditions;
	}

	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	/**
	 * @return the exchangeRate
	 */
	public String getExchangeRate() {
		return exchangeRate;
	}

	/**
	 * @param exchangeRate the exchangeRate to set
	 */
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	/**
	 * @return the son
	 */
	public String getSon() {
		return son;
	}

	/**
	 * @param son the son to set
	 */
	public void setSon(String son) {
		this.son = son;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the canceled
	 */
	public String getCanceled() {
		return canceled;
	}

	/**
	 * @param canceled the canceled to set
	 */
	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	/**
	 * @return the subtotal
	 */
	public String getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * @return the igv
	 */
	public String getIgv() {
		return igv;
	}

	/**
	 * @param igv the igv to set
	 */
	public void setIgv(String igv) {
		this.igv = igv;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the idConf
	 */
	public int getIdConf() {
		return idConf;
	}

	/**
	 * @param idConf the idConf to set
	 */
	public void setIdConf(int idConf) {
		this.idConf = idConf;
	}

	/**
	 * @return the idCustumer
	 */
	public int getIdCustumer() {
		return idCustumer;
	}

	/**
	 * @param idCustumer the Custumer to set
	 */
	public void setIdCustumer(int idCustumer) {
		this.idCustumer = idCustumer;
	}

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
	
}
