package ar.edu.unju.fi.POO2020C01Equipo01.patronFactoryMethod;

import java.util.List;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpLevelDto;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoLevelManager;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoRegionManager;

public interface FabricaInforme {
	public static final String PATH_INFORME = "C:/INFORME-EMPLEADOS/";
	public abstract Boolean generarInforme(List<EmpleadoDTO> lista, String descripcion);
	public abstract Boolean generarInforme(EmpleadoRegionManager emp, String descripcion);
    public abstract Boolean generarInforme(EmpleadoLevelManager emp, String descripcion) throws ServiceException;
}
