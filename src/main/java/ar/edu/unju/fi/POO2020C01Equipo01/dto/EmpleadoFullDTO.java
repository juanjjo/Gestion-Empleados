package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import java.io.Serializable;
import java.time.LocalDate;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Departamento;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;

public class EmpleadoFullDTO implements Serializable {

	
	private String nombre;
	private String apellido;
	private String  eMail;
	private String numeroTelefono;
	private LocalDate fechaContratacion;
	private String titulo;
	private Double salary;
	private Integer comision;
	private Long idDep;
	private Long idMan;
	
	
public EmpleadoFullDTO() {
	// TODO Auto-generated constructor stub
}

	public EmpleadoFullDTO(String nombre, String apellido, String eMail, String numeroTelefono,
			LocalDate fechaContratacion, String titulo, Double salary, Integer comision, Long idDep, Long idMan) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.eMail = eMail;
		this.numeroTelefono = numeroTelefono;
		this.fechaContratacion = fechaContratacion;
		this.titulo = titulo;
		this.salary = salary;
		this.comision = comision;
		this.idDep = idDep;
		this.idMan = idMan;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getComision() {
		return comision;
	}

	public void setComision(Integer comision) {
		this.comision = comision;
	}

	public Long getIdDep() {
		return idDep;
	}

	public void setIdDep(Long idDep) {
		this.idDep = idDep;
	}

	public Long getIdMan() {
		return idMan;
	}

	public void setIdMan(Long idMan) {
		this.idMan = idMan;
	}	
}
