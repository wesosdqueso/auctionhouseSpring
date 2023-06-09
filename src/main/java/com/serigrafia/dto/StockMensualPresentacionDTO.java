package com.serigrafia.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class StockMensualPresentacionDTO {
	
	private int id;
	
	@NotBlank
	private Date mes;
	
	@NotBlank
	private int cantidad;
	
	private AlmacenDTO almacen;
	
	private PresentacionDTO presentacion;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getMes() {
		return this.mes;
	}
	
	public void setMes(Date mes) {
		this.mes = mes;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public AlmacenDTO getAlmacen() {
		return this.almacen;
	}
	
	public void setAlmacen(AlmacenDTO almacen) {
		this.almacen = almacen;
	}
	
	public PresentacionDTO getPresentacion() {
		return this.presentacion;
	}
	
	public void setPresentacion(PresentacionDTO presentacion) {
		this.presentacion = presentacion;
	}
	
}
