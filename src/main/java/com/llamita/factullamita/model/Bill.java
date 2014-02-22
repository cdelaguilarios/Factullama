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
	private int idClient;
	
}
