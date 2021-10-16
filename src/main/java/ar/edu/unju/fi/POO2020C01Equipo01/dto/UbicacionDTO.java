package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import java.io.Serializable;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Pais;

public class UbicacionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String direccion;
	private Integer codigoPosta;
	private String ciudad;
	private String provincia;
	private String pais;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getCodigoPosta() {
		return codigoPosta;
	}
	public void setCodigoPosta(Integer codigoPosta) {
		this.codigoPosta = codigoPosta;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
}
