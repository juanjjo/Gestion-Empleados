package ar.edu.unju.fi.POO2020C01Equipo01.dto;


public class EmpleadoModificarDTO {
	private Long id;
	private String nombre;
	private String apellido;
	private String eMail;
	private String numeroTelefono;
	private Double salary;
	public EmpleadoModificarDTO() {
		// TODO Auto-generated constructor stub
	}
	public EmpleadoModificarDTO(Long id, String nombre, String apellido, String eMail, String numeroTelefono,Double salary) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.eMail = eMail;
		this.numeroTelefono = numeroTelefono;
		this.salary = salary;
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
	
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmpleadoModificarDTO [nombre=" + nombre + ", apellido=" + apellido + ", eMail=" + eMail
				+ ", numeroTelefono=" + numeroTelefono + ", salary=" + salary + ", id" + id +"]";
	}
	
}
