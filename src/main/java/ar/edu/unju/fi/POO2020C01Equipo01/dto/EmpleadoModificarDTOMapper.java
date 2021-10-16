package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Trabajo;

public class EmpleadoModificarDTOMapper {
	
	public EmpleadoModificarDTO map (Empleado empp,Trabajo trabajo) {
		EmpleadoModificarDTO emp  = new EmpleadoModificarDTO();
		emp.setNombre(empp.getNombre());
		emp.setApellido(empp.getApellido());
		emp.seteMail(empp.geteMail());
		emp.setNumeroTelefono(empp.getNumeroTelefono());
		emp.setSalary(empp.getSalary());
		return emp;
	}
	
	public Empleado mapEmpleado (Empleado empleado,EmpleadoModificarDTO empp) {
		if(empp.getNombre()!=null)
		empleado.setNombre(empp.getNombre());
		if(empp.getApellido()!=null)
		empleado.setApellido(empp.getApellido());
		if(empp.geteMail()!=null)
		empleado.seteMail(empp.geteMail());
		if(empp.getNumeroTelefono()!=null)
		empleado.setNumeroTelefono(empp.getNumeroTelefono());
		if(empp.getSalary()!=null)
		empleado.setSalary(empp.getSalary());
		return empleado;
	}
}
