package ar.edu.unju.fi.POO2020C01Equipo01.entity;

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
@Table(name = "user_autentication")
public class AutenticacionUsuario {
	@Id
	@Column(name = "autentication_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	@Column(name = "userName")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "active")
	private boolean active;
	//relacion un autenticacion tiene un rol
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_id")
	private Rol rol;
	
	//relacion un autenticacion tiene un empleado
	 @JoinColumn(name = "employee_id")
	 @OneToOne(fetch = FetchType.LAZY)
	 private Empleado empleado;
	
	public AutenticacionUsuario() {
		// TODO Auto-generated constructor stub
	}

	public AutenticacionUsuario(long id, String userName, String password, boolean active, Empleado empleado) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.empleado = empleado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	
}
