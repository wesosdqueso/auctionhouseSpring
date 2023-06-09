package com.serigrafia.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class StockEnvaseDTO {
	
	private int id;
	
	@NotBlank
	private int cantidad;
	
	private EnvaseDTO envase;
	
	private AlmacenDTO almacen;
	
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
	
	public EnvaseDTO getEnvase() {
		return this.envase;
	}
	
	public void setEnvase(EnvaseDTO envase) {
		this.envase = envase;
	}
	
	public AlmacenDTO getAlmacen() {
		return this.almacen;
	}
	
	public void setAlmacen(AlmacenDTO almacen) {
		this.almacen = almacen;
	}
	
}
