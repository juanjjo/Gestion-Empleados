package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import java.io.Serializable;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Region;

public class PaisDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String nombre;
	private long region;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getRegion() {
		return region;
	}

	public void setRegion(long region) {
		this.region = region;
	}
	
}