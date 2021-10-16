package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;

public class EmpleadoDTOMapper {
    
	public EmpleadoDTOMapper() {
	
	}
	
	public EmpleadoDTO map(Empleado empleado)
	{
		EmpleadoDTO empDto = new EmpleadoDTO();
		empDto.setFullname(empleado.getApellido() +" " + empleado.getNombre());
		empDto.seteMail(empleado.geteMail());
		empDto.setId(empleado.getId());
	    empDto.setSalary(empleado.getSalary());
		try {
		empDto.setNombreDepartamento(empleado.getDepartamento().getNombre());
		}
		catch (Exception e) {
			empDto.setNombreDepartamento("Sin departamento");
		}
		empDto.setNombreTrabajo(empleado.getTrabajo().getTitulo());
		
		return empDto;
	}
	
	public List<EmpleadoDTO> convertList(List<Empleado> lista)
	{   List<EmpleadoDTO> listDTO = new ArrayList<EmpleadoDTO>();
		for( Empleado emp : lista )
			listDTO.add(map(emp));
		return listDTO;
	}
}
