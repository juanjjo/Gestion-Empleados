package ar.edu.unju.fi.POO2020C01Equipo01.Sprint4;

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

import ar.edu.unju.POO2020C01Equipo01.enums.TiposInforme;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.EmpleadoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.RegionRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.AutenticacionUsuarioDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.DepartamentoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpLevelDto;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoFullDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoModificarDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.HistorialTrabajoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.AutenticacionUsuario;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Region;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoLevelManager;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoRegionManager;
import ar.edu.unju.fi.POO2020C01Equipo01.patronFactoryMethod.FabricaInforme;
import ar.edu.unju.fi.POO2020C01Equipo01.service.HistorialTrabajoService;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.DepartamentServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.EmpleadoServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.InformeFactoryMethodServiceImp;

@SpringBootTest
@RunWith(JUnitPlatform.class)
public class Poo2020C01Equipo01ApplicacionTestsSprint4 {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Poo2020C01Equipo01ApplicacionTestsSprint4.class);

	@Autowired
	EmpleadoServiceImp empServ;
	
	@Autowired
	DepartamentServiceImp depServ;

	@Autowired
	HistorialTrabajoService histJobServ;
	
	InformeFactoryMethodServiceImp informeService;
	private List<EmpleadoDTO> listaPunto1;
	private List<EmpleadoDTO> listaPunto2;
	private EmpleadoRegionManager empMan;
	private DepartamentoDTO departmentDTO;
	/**
	 * Inicializamos el informeService sin autowired, devido que no
	 * informe no es una ENTITY
	 * cargamos la lista 1, inicializamos empMan
	 * y cargamos el departamentoDTO que usarmos para buscar empleados con sueldo mayor al promedio del departamento mismo
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		informeService = new InformeFactoryMethodServiceImp();
		listaPunto1 = empServ.buscarEmpleadosSalarioMayorPromedio((long)100);
	    departmentDTO = depServ.buscarDepartamento((long)100);
		empMan = new EmpleadoRegionManager();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * verificar casosde la tabla antes de ejecutar este tes
	 */
	//@Test
	public void testearBusquedas()
	{
		assertTrue(empServ.buscarEmpleadosCambioJob((long)2).size()==4);
		assertTrue(empServ.buscarEmpleadosSalarioMayorPromedio((long)100).size()==2);
		assertEquals(empServ.buscarEmpleadosAgrupadosRegion().getListRegion1().size(), 36);
	}
	
	@Test
	public void testGenerarInforme1PDF()
	{   
		TiposInforme tipo = TiposInforme.PDF;
		FabricaInforme informe = informeService.getInstance(tipo);
		String descripcion = "Lista de empleados que superan el salario promedio de su departamento : " + departmentDTO.getNombre();
		assertTrue( informe.generarInforme(listaPunto1, descripcion));
	}

	@Test
	public void testgenerarInforme2PDF()
	{
		int cantidadCambioTrabajo = 2; 
		TiposInforme tipo = TiposInforme.PDF;
		FabricaInforme informe = informeService.getInstance(tipo);
		String descripcion = "En este listado se muetran los empleados que cambiaron de trabajo mas de " + cantidadCambioTrabajo + " veces";
		listaPunto2 = empServ.buscarEmpleadosCambioJob(cantidadCambioTrabajo);
		assertTrue(informe.generarInforme(listaPunto2, descripcion));
	}
	
	@Test
	public void testgenerarInforme3PDF()
	{
		TiposInforme tipo = TiposInforme.PDF;
		FabricaInforme informe = informeService.getInstance(tipo);
		empMan = empServ.buscarEmpleadosAgrupadosRegion();
		String descripcion = "Empleados agrupados por region";
		assertTrue(informe.generarInforme(empMan, descripcion));
	}
	
	@Test
	public void testGenerarInforme4PDF() throws ServiceException
	{
		EmpleadoLevelManager empLevelMan = empServ.busquedaDesdeManager((long)101);
		TiposInforme tipo = TiposInforme.PDF;
		FabricaInforme informe = informeService.getInstance(tipo);
		String descripcion = "Lista jerárquica de todos los empleados que dependen del empleado de cabecera.";
		assertTrue(informe.generarInforme(empLevelMan, descripcion));
	}
	
	
	@Test
	public void testGenerarInforme1Exel() { 
		TiposInforme tipo = TiposInforme.EXEL;
		FabricaInforme informe = informeService.getInstance(tipo);
		String descripcion = "Lista de empleados que superan el salario promedio de su departamento : " + departmentDTO.getNombre();
		assertTrue( informe.generarInforme(listaPunto1, descripcion));
	}
	
	@Test
	public void testgenerarInforme2Exel()
	{
		long cantidadCambioTrabajo = 2;
		TiposInforme tipo = TiposInforme.EXEL;
		FabricaInforme informe = informeService.getInstance(tipo);
		String descripcion = "En este listado se muetran los empleados que cambiaron de trabajo mas de " + cantidadCambioTrabajo + " veces";
		listaPunto2 = empServ.buscarEmpleadosCambioJob(cantidadCambioTrabajo);
		assertTrue(informe.generarInforme(listaPunto2, descripcion));
	}
	
	@Test
	public void testGenerarInforme3Exel() {
		TiposInforme tipo = TiposInforme.EXEL;
		FabricaInforme informe = informeService.getInstance(tipo);
		String descripcion = "Lista de Empleado de una determinada region";
		empMan = empServ.buscarEmpleadosAgrupadosRegion();
		assertTrue(informe.generarInforme(empMan, descripcion));
	}
	
	
	@Test
	public void testGenerarInforme4Exel() throws ServiceException{
		EmpleadoLevelManager empLevelMan = empServ.busquedaDesdeManager((long)101);
		TiposInforme tipo = TiposInforme.EXEL;
		FabricaInforme informe = informeService.getInstance(tipo);
		String descripcion = "Lista jerárquica de todos los empleados que dependen del empleado de cabecera.";
		assertTrue(informe.generarInforme(empLevelMan, descripcion));
	}
}
