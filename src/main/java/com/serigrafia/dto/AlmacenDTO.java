package com.serigrafia.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class AlmacenDTO {
	
	private int id;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String direccion;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
