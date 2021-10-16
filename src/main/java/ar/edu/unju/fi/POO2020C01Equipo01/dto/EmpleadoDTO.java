package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import java.io.Serializable;
import java.time.LocalDate;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Departamento;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;

public class EmpleadoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String fullname;
	private String eMail;
	private String nombreTrabajo;
	private String nombreDepartamento;
	// MODIFICACIONES PARA TRABAJAR CON SALARY
	private double salary;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getNombreTrabajo() {
		return nombreTrabajo;
	}
	public void setNombreTrabajo(String nombreTrabajo) {
		this.nombreTrabajo = nombreTrabajo;
	}
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "EmpleadoDTO [id=" + id + ", fullname=" + fullname + ", eMail=" + eMail + ", nombreTrabajo="
				+ nombreTrabajo + ", nombreDepartamento=" + nombreDepartamento + ", salary=" + salary + "]";
	}
	
	
	
}
