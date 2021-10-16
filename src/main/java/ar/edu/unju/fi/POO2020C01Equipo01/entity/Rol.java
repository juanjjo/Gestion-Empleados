package ar.edu.unju.fi.POO2020C01Equipo01.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {
	@Id
	@Column(name = "rol_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name = "name")
	private String nombre;
	
	@OneToMany(mappedBy = "rol")
	private List<AutenticacionUsuario> autenticaciones= new ArrayList<AutenticacionUsuario>();
	
	public Rol() {
		// TODO Auto-generated constructor stub
	}
	
	public Rol(long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<AutenticacionUsuario> getAutenticaciones() {
		return autenticaciones;
	}
	public void setAutenticaciones(List<AutenticacionUsuario> autenticaciones) {
		this.autenticaciones = autenticaciones;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre + ", autenticaciones=" + autenticaciones + "]";
	}
	
}
