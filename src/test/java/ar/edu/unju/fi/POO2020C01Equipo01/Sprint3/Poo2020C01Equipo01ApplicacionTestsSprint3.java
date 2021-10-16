package ar.edu.unju.fi.POO2020C01Equipo01.Sprint3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.AutenticacionUsuarioDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.DepartamentoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoFullDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoModificarDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.HistorialTrabajoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.service.HistorialTrabajoService;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.AutenticacionUsuarioServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.DepartamentServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.EmpleadoServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.RolServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.util.EmcriptacionMd5Util;

@SpringBootTest
@RunWith(JUnitPlatform.class)
public class Poo2020C01Equipo01ApplicacionTestsSprint3 {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Poo2020C01Equipo01ApplicacionTestsSprint3.class);

	@Autowired
	EmpleadoServiceImp emp;

	@Autowired
	DepartamentServiceImp dep;
	
	@Autowired
	AutenticacionUsuarioServiceImp autenticacionServ;
	
	@Autowired
	HistorialTrabajoService histJobServ;

	EmcriptacionMd5Util util;

	AutenticacionUsuarioDTO autenticacionDto;

	EmpleadoDTO empNuevoDto;
	
	EmpleadoModificarDTO empMod;
	EmpleadoDTO empDTO = new EmpleadoDTO();
	long id;
	@BeforeEach
	void setUp() throws Exception {
		empNuevoDto = new EmpleadoDTO();
		id= 122;
		empMod = new EmpleadoModificarDTO(id, "Empleado2", "Modificado2","Empleado2Mod@gmail", null, (double)5000) ;
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	//@Test
	void testAltaEmpleado() {
		try {
		
			LocalDate fechaContratacion = LocalDate.now();
			Long idDep=(long) 10; 
			Long idMan=(long) 200;
			EmpleadoFullDTO empfull1 = new EmpleadoFullDTO("Guillermo","Franchela","gfranch@gmail.com","4235433",fechaContratacion,"FI_ACCOUNT",1500.0,null,idDep,idMan);
			empNuevoDto=emp.contratarNuevoEmpleado(empfull1);
			assertTrue(empNuevoDto.getFullname().equals("Franchela Guillermo"));
		} catch (Exception e) {
			log.error(e.getCause());
		}		
	}
	
	
	/**
	 * si hay parametros que no queremos modificar del empleado, enviar todo null
	 * si desea no modificar el trabajo del empleado envie su id actual
	 */
	//@Test
	void testModificarEmpleado() {
		String idTrabajo = "ST_CLERK"; //ID NUEVO TRABAJO
		try {
		    emp.modificarEmpleadoo(empMod, idTrabajo);
			empDTO.setId(id);
		    assertTrue(histJobServ.buscarHistorialDeEmpleado(empDTO).size()==3);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	
	/**
	 * Este test corresponde al punto 3 del sprint 3, en este caso al
	 * buscar el historial de los trabajos de un empleado, tener en cuenta
	 * que tambien extrae el trabajo actual.
	 */
	@Test
	void testBuscarHistorialEmpleado()
	{
		List<HistorialTrabajoDTO> listDTO = new ArrayList<HistorialTrabajoDTO>();
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		empleadoDTO.setId((long)101);
		try {
		listDTO = histJobServ.buscarHistorialDeEmpleado(empleadoDTO);
		assertTrue(listDTO.size()==3);
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Para comprobar el test ejecutar el siguiente script en la bd
	 * SELECT * FROM departments WHERE department_name LIKE '%IT%' AND location_id=1700;
	 */
	@Test
	public void busquedaDepartmentTest()
	{   
		List<DepartamentoDTO> listDto = new ArrayList<DepartamentoDTO>();
		try {
		listDto = dep.buscarSegunParametros("IT",(long) 1700);
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}
		finally {
		assertEquals(2,listDto.size());
		}
	}
	
	/**
	 * Test de busqueda por ocurrencia, con 3 parametros,
	 *  si algun parametro es nulo no se lo tiene en cuenta
	 *  si todos son nulos, extrae todas las tuplas.
	 *  tener en cuenta que los numeros de comparacion puede 
	 *  variar,debido a los cambios realizados en la base de datos
	 */
	@Test
	public void testBusquedaOcurrenciaok()
	{
		List<Long> listDepartment = new ArrayList<Long>() ;
		listDepartment.add((long)100);
		listDepartment.add((long)90);
		listDepartment.add((long)80);
		// en cuenta que si crea un empleado debe aumentar cantidad.
    	assertEquals(109, emp.busquedaOcurrencia(null, null,null).size()); 
		assertEquals(14, emp.busquedaOcurrencia(null, null,(long) 100).size());
		assertEquals(42, emp.busquedaOcurrencia(null, listDepartment,null).size());  
		assertEquals(3, emp.busquedaOcurrencia("John", null, null).size());  
		assertEquals(2, emp.busquedaOcurrencia("David", listDepartment,null).size());  
		assertEquals(1, emp.busquedaOcurrencia("David", null,(long)103).size()); 
		assertEquals(1, emp.busquedaOcurrencia("David", listDepartment,(long)145).size()); 
	}

}
