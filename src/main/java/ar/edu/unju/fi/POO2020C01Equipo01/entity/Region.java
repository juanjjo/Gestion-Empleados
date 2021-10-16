package ar.edu.unju.fi.POO2020C01Equipo01.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
public class Region {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name = "region_id")
	private Long id;
	
	@Column(name = "region_name")
	private String nombre;
	
	@OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
	private List<Pais> paises = new ArrayList<Pais>();
	
	public Region(){
		
	}
	
	public Region(String nombre) {
		this.nombre=nombre;
	}

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
	
	
	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", nombre=" + nombre + ", paises=" + paises + "]";
	}

	
}
