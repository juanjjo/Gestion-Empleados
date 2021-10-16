package ar.edu.unju.fi.POO2020C01Equipo01.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpLevelDto;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoFullDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoModificarDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoLevelManager;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoRegionManager;

public interface EmpleadoService {
   /**
    * Servicio que permite buscar los empleados de un manager determinado
    * @param id   -  correspontiente al managet
    * @return  lista de empleados que trabajan con el manager en formato EmpleadoDTO
    */
   List<EmpleadoDTO> buscarEmpleadosManager(long id);
   /**
    * El Servicio contrata un empleado EXISTENTE de la base de datos, y genera el historial
    * de contratacion
    * @param empNuevo  - empleado que existe en la base de datos pero no fue contratado nunca
    * @return null si falla la operacion   - empleado contratado en formado DTO
    * @throws ServiceException
    */
   public EmpleadoDTO contratarNuevoEmpleado(EmpleadoFullDTO empNuevo) throws ServiceException;
   /**
    * Este servicio busca un empleado de la base segun su id
    * @param id
    * @return null o exeption - empleado encontrado en format DTO
    * @throws ServiceException
    */
   EmpleadoDTO buscarEmpleadoId(long id) throws ServiceException;
  
   /**
    * Este servicio es una busqueda segun parametros, ignorando los parametros nulos
    * ejemplos : 
    * 
    * si enviamos solo la lista de departamento, traera los empleados que trabajan en ese departameto
    * sin importar el nombre, o el manager.
    * 
    * @param nombre - representa al nombre que pueden tener los usuarios
    * @param listDep  -  es una lista de departamentos, en los que se desea consultar
    * @param idManager - representa el manager, si queremos filtrar tambien por los empleados que trabajan para un manager
    * @return lista de EmpleadosDTO 
    * 
    * Nota : Si enviamos todos los parametros nulos, extrae todos los empleados
    */
   List<EmpleadoDTO> busquedaOcurrencia(String nombre, List<Long> listDep, Long idManager);
   
   /**
    * Trae una lista de empleados que cambiaron de trabajo n veces o mas de n veces.
    * @param cantidadVeces
    * @return
    */
   List<EmpleadoDTO> buscarEmpleadosCambioJob(long cantidadVeces);
   
   /**
    * trae empleados cuyo salario es mayor al promedio de salarios
    * de su departamento
    * @return
    */
   List<EmpleadoDTO> buscarEmpleadosSalarioMayorPromedio(long department_id);
   /**
    * trae empleados agrupados por regiones.
    * @return
    */
   EmpleadoRegionManager buscarEmpleadosAgrupadosRegion();
   
   EmpleadoLevelManager busquedaDesdeManager(long idManager);
   
}
