package com.llamita.factullamita.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTE")
public class Customer  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -463762234326019616L;

	@Id
	@GeneratedValue
	@Column(name="IN_IDCLIENTE")
	private Integer id;
	
	@Column(name="VC_NOMBRE")
	private String name;
	
	@Column(name="VC_RUC")
	private String ruc;
	
	@Column(name="VC_DIRECCION")
	private String address;
	
	@Column(name="CH_ESTADO")
	private String state;
	
	public Customer(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
