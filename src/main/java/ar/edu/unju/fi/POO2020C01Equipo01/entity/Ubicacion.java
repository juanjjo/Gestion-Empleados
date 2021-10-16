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
@Table(name = "locations")
public class Ubicacion {
	@Id
	@Column(name = "location_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "street_address")
	private String direccion;
	
	@Column(name ="postal_code")
	private Integer codigoPostal;
	
	@Column(name ="city")
	private String ciudad;
	
	@Column(name="state_province")
	private String provincia;
	
	@OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL)
	private List<Departamento> departamentos = new ArrayList<Departamento>();
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Pais pais;
	
	/*@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Pais pais;*/
	
	public Ubicacion(){
		
	}
	
	public Ubicacion (String direccion, Integer codigoPostal,String ciudad,
			String provincia,Pais pais) {
		this.direccion=direccion;
		this.codigoPostal=codigoPostal;
		this.ciudad=ciudad;
		this.provincia=provincia;
		this.pais=pais;
	}

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

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
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

	
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Ubicacion [id=" + id + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", ciudad="
				+ ciudad + ", provincia=" + provincia + ", departamentos=" + departamentos + ", pais=" + pais + "]";
	}

}
