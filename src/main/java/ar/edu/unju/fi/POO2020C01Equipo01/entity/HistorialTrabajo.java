package ar.edu.unju.fi.POO2020C01Equipo01.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "job_history")
public class HistorialTrabajo {
	
	@EmbeddedId
	private HistorialTrabajoPk pk = new HistorialTrabajoPk();
	
	@Column(name ="start_date",insertable = false, updatable = false)
	@MapsId("start_date")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	@MapsId("employee_id")
	private Empleado empleado;

	
	@Column(name = "end_date")
	private LocalDate fechaFinzalizada;
	
	//relacion historial tiene un trabajo
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name =  "job_id")
	private Trabajo trabajo;
	
	//relacion un historial tiene un departamento
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Departamento departamentoo;
	
	
	public HistorialTrabajo() {
		// TODO Auto-generated constructor stub
	}

	public HistorialTrabajo(HistorialTrabajoPk pk, LocalDate fechaFinzalizada, Trabajo trabajo,
			Departamento departamentoo) {
		super();
		//this.pk = pk;
		this.fechaFinzalizada = fechaFinzalizada;
		this.trabajo = trabajo;
		this.departamentoo = departamentoo;
	}

	


	public HistorialTrabajoPk getPk() {
		return pk;
	}

	public void setPk(HistorialTrabajoPk pk) {
		this.pk = pk;
	}

	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public LocalDate getFechaFinzalizada() {
		return fechaFinzalizada;
	}

	public void setFechaFinzalizada(LocalDate fechaFinzalizada) {
		this.fechaFinzalizada = fechaFinzalizada;
	}

	public Trabajo getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}

	public Departamento getDepartamentoo() {
		return departamentoo;
	}

	public void setDepartamentoo(Departamento departamentoo) {
		this.departamentoo = departamentoo;
	}

	@Override
	public String toString() {
		return "HistorialTrabajo [pk=" + pk + ", date=" + date + ", empleado=" + empleado + ", fechaFinzalizada="
				+ fechaFinzalizada + ", trabajo=" + trabajo + ", departamentoo=" + departamentoo + "]";
	}

	
}
