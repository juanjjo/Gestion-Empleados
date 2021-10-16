package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Departamento;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Trabajo;

public class EmpleadoFullDTOMapper {
	public EmpleadoFullDTOMapper() {
		// TODO Auto-generated constructor stub
	}
	
	public Empleado map (EmpleadoFullDTO empFDto,Departamento departamento,Empleado man,Trabajo tra) {
		
		Empleado emp = new Empleado();
		emp.setNombre(empFDto.getNombre());
		emp.setApellido(empFDto.getApellido());
		emp.seteMail(empFDto.geteMail());
		emp.setNumeroTelefono(empFDto.getNumeroTelefono());
		emp.setFechaContratacion(empFDto.getFechaContratacion());
		emp.setTrabajo(tra);
		emp.setSalary(empFDto.getSalary());
		//emp.setComision(empFDto.getComision());
		emp.setDepartamento(departamento);
		emp.setManager(man);
		return emp;
	}
}
