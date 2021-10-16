package ar.edu.unju.fi.POO2020C01Equipo01.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Trabajo {
	@Id	
	@Column(name = "job_id")
	private String id;
	
	@Column(name = "job_title")
	private String titulo;
	
	@Column(name = "min_salary")
	private Double salarioMin;
	
	@Column(name = "max_salary")
	private Double salarioMax;
	
	@OneToMany(mappedBy = "trabajo")
	private List<HistorialTrabajo> historialtrabajos = new ArrayList<HistorialTrabajo>();
	
	@OneToMany(mappedBy = "trabajo")
	private List<Empleado> empleados = new ArrayList<Empleado>();
	
	public Trabajo() {
		// TODO Auto-generated constructor stub
	}

	public Trabajo(String id, String titulo, Double salarioMin, Double salarioMax) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.salarioMin = salarioMin;
		this.salarioMax = salarioMax;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	@Override
	public String toString() {
		return "Trabajo [id=" + id + ", titulo=" + titulo + ", salarioMin=" + salarioMin + ", salarioMax=" + salarioMax
				+ "]";
	}
	
	
	
}
