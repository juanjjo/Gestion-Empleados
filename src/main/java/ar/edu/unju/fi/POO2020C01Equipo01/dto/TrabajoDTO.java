package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import java.io.Serializable;

public class TrabajoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private Double salarioMin;
	private Double salarioMax;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Double getSalarioMin() {
		return salarioMin;
	}
	public void setSalarioMin(Double salarioMin) {
		this.salarioMin = salarioMin;
	}
	public Double getSalarioMax() {
		return salarioMax;
	}
	public void setSalarioMax(Double salarioMax) {
		this.salarioMax = salarioMax;
	}
	
	
	
}
