package com.serigrafia.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class MarcaDTO {
	
	private int id;
	
	@NotBlank
	private String nombre;
	
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
	
}
