package ar.edu.unju.fi.POO2020C01Equipo01.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "employees")
public class Empleado {
	
	
	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name = "first_name")
	private String nombre;
	
	@Column(name ="last_name")
	private String apellido;
	
	@Column(name ="email")
	private String eMail;
	
	@Column(name ="phone_number")
	private String numeroTelefono;
	
	@Column(name ="hire_date")
	private LocalDate fechaContratacion;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "job_id")
	private Trabajo trabajo;
	
	
	@Column(name ="salary")
	private double salary;
	
	//@Column(name ="commission_pct")
	//private double comision;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	private Departamento departamento;
	
	//relaciion Empleado manager reflexiva
	@ManyToOne
	@JoinColumn(name="manager_id", referencedColumnName="employee_id")
	private Empleado manager;
	
	@OneToOne(mappedBy = "empleado")
	private AutenticacionUsuario autenticacion;
	
	public Empleado() {
		
	}
	
	public Empleado(long id, String nombre, String apellido, String eMail, String numeroTelefono,
			LocalDate fechaContratacion, Trabajo trabajo, double salary, Departamento departamento, Empleado manager,
			AutenticacionUsuario autenticacion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.eMail = eMail;
		this.numeroTelefono = numeroTelefono;
		this.fechaContratacion = fechaContratacion;
		this.trabajo = trabajo;
		this.salary = salary;
		this.departamento = departamento;
		this.manager = manager;
		this.autenticacion = autenticacion;
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
	
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public Empleado getManager() {
		return manager;
	}
	public void setManager(Empleado manager) {
		this.manager = manager;
	}
	
	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public Trabajo getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}

	public AutenticacionUsuario getAutenticacion() {
		return autenticacion;
	}

	public void setAutenticacion(AutenticacionUsuario autenticacion) {
		this.autenticacion = autenticacion;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", eMail=" + eMail
				+ ", numeroTelefono=" + numeroTelefono + ", fechaContratacion=" + fechaContratacion + ", trabajo="
				+ trabajo + ", salary=" + salary + ", departamento=" + departamento.getNombre()
				+ ", manager=" + manager + ", autenticacion=" + autenticacion + "]";
	}

	

}
