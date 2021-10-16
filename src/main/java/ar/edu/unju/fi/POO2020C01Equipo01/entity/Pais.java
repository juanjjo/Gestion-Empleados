package ar.edu.unju.fi.POO2020C01Equipo01.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Pais {
	@Id	
	@Column(name = "country_id")
	private String id;
	
	@Column(name = "country_name")
	private String nombre;
	
	
	@OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
	private List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	private Region region;
	
	public Pais() {
		
	}
	
	public Pais(String nombre,Region region) {
		this.nombre = nombre;
		this.region=region;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nombre=" + nombre + ", ubicaciones=" + ubicaciones + ", region=" + region + "]";
	}

	
	
}
