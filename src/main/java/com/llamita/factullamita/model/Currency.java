package com.llamita.factullamita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MONEDA")
public class Currency {

	@Id
	@GeneratedValue
	@Column(name="IN_IDMONEDA")
	private Integer id;
	
	@Column(name="VC_PAIS")
	private String country;
	
	@Column(name="VC_SIMBOLO")
	private String symbol;
	
	@Column(name="VC_DENOMINACION")
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
