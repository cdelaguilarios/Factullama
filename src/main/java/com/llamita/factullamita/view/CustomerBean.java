package com.llamita.factullamita.view;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerBean implements Serializable{

	private static final long serialVersionUID = 2458402754973069055L;

	private int id;
	
	@NotNull
	@Size(min=1,max=100,message="El nombre es obligatorio debe tener un máximo de 100 caracteres.")
	private String name;
	
	@NotNull
	@Size(min=1,max=11,message="El RUC debe contar con 11 dígitos.")
	private String ruc;
	
	@NotNull
	@Size(min=1,max=200,message="La dirección es obligatoria debe tener un máximo de 200 caracteres.")
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
