package com.serigrafia.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class PresentacionDTO {
	
	private int id;
	
	@NotBlank
	private String descripcion;
	
	private MarcaDTO marca;
	
	private EnvaseDTO envase;
	
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
	
	public MarcaDTO getMarca() {
		return this.marca;
	}
	
	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}
	
	public EnvaseDTO getEnvase() {
		return this.envase;
	}
	
	public void setEnvase(EnvaseDTO envase) {
		this.envase = envase;
	}
	
}
