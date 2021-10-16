package ar.edu.unju.fi.POO2020C01Equipo01.service;

import java.util.List;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.RolDto;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Rol;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;


public interface RolService {
   public RolDto guardar(RolDto obj) throws ServiceException;
   public List<Rol> obtenerTodos();
}
