package com.serigrafia.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class EnvaseDTO {
	
	private int id;
	
	@NotBlank
	private String descripcion;
	
	private ProductorDTO productor;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public ProductorDTO getProductor() {
		return this.productor;
	}
	
	public void setProductor(ProductorDTO productor) {
		this.productor = productor;
	}
	
}
