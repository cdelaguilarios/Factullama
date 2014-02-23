package com.llamita.factullamita.view;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2458402754973069055L;

	private int id;
	
	@NotNull
	@Size(min=1,max=100)
	private String name;
	
	@NotNull
	@Size(min=1,max=11)
	private String ruc;
	
	@NotNull
	@Size(min=1,max=200)
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	
	
}
