package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import java.io.Serializable;

public class RegionDTO implements Serializable{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
