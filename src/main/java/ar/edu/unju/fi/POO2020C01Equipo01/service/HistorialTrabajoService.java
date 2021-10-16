package ar.edu.unju.fi.POO2020C01Equipo01.service;

import java.util.List;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.HistorialTrabajoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.HistorialTrabajo;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;

public interface HistorialTrabajoService {
    
	public HistorialTrabajo guardarNuevoHistorial(Empleado obj);
	public List<HistorialTrabajoDTO> buscarHistorialDeEmpleado(EmpleadoDTO emp) throws ServiceException;
}
