package com.serigrafia.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class MovimientoPresentacionDTO {
	
	private int id;
	
	@NotBlank
	private int cantidad;
	
	@NotBlank
	private Date fecha;
	
	private String numGuia;
	
	private PresentacionDTO presentacion;
	
	private AlmacenDTO almacenOrigen;
	
	private AlmacenDTO almacenDestino;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Date getFecha() {
		return this.fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getNumGuia() {
		return this.numGuia;
	}
	
	public void setNumGuia(String numGuia) {
		this.numGuia = numGuia;
	}
	
	public PresentacionDTO getPresentacion() {
		return this.presentacion;
	}
	
	public void setPresentacion(PresentacionDTO presentacion) {
		this.presentacion = presentacion;
	}
	
	public AlmacenDTO getAlmacenOrigen() {
		return this.almacenOrigen;
	}
	
	public void setAlmacenOrigen(AlmacenDTO almacenOrigen) {
		this.almacenOrigen = almacenOrigen;
	}
	
	public AlmacenDTO getAlmacenDestino() {
		return this.almacenDestino;
	}
	
	public void setAlmacenDestino(AlmacenDTO almacenDestino) {
		this.almacenDestino = almacenDestino;
	}
	
}
