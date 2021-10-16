package ar.edu.unju.fi.POO2020C01Equipo01.service;

import java.util.List;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.DepartamentoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;

public interface DepartmentService {
    public List<DepartamentoDTO> buscarDepartamentosEmpleado(Long id);
    public DepartamentoDTO actualizar(DepartamentoDTO d);
    DepartamentoDTO buscarDepartamento (Long id) throws ServiceException;
    DepartamentoDTO findByNombre(String nombre);
    List<DepartamentoDTO> buscarSegunParametros(String nombreDep, Long id) throws ServiceException;
}
