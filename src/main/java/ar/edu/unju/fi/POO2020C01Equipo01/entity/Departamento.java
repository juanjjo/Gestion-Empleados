package ar.edu.unju.fi.POO2020C01Equipo01.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "departments")
public class Departamento {
	@Id
	@Column(name = "department_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name = "department_name")
	private String nombre;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Ubicacion ubicacion;
	
	//relacion departamento muchos empleados
	//, cascade = CascadeType.ALL
	@OneToMany(mappedBy = "departamento")
	private List<Empleado> empleados = new ArrayList<Empleado>();
		
		
	//relacion departamento tiene un manager
	@ManyToOne
	@JoinColumn(name="manager_id", referencedColumnName="employee_id")
	private Empleado manager;
	
	public Departamento() {
		
	}
	
	public Departamento(String nombre, Ubicacion ubicacion) {
		this.nombre=nombre;
		this.ubicacion=ubicacion;
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
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado getManager() {
		return manager;
	}

	public void setManager(Empleado manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", empleados=" + empleados
				+ ", manager=" + manager + "]";
	}
	
}
