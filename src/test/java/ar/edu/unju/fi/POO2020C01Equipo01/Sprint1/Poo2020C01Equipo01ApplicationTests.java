package ar.edu.unju.fi.POO2020C01Equipo01.Sprint1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTOMapper;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoFullDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoModificarDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.HistorialTrabajoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.RolDto;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.AutenticacionUsuario;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.HistorialTrabajo;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Rol;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Trabajo;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.service.HistorialTrabajoService;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.AutenticacionUsuarioServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.DepartamentServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.EmpleadoServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.RolServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.util.EmcriptacionMd5Util;

@SpringBootTest
@RunWith(JUnitPlatform.class)
class Poo2020C01Equipo01ApplicationTests {

	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Poo2020C01Equipo01ApplicationTests.class);

	@Autowired
	EmpleadoServiceImp emp;

	@Autowired
	DepartamentServiceImp dep;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Este test modifica un departameto, primero lo busca en la base de datos, si
	 * no lo encuentra se produce una ServiceException y muestra en un log un
	 * mensaje de error
	 */
	@Test
	void testModificarUnDepartamento() {
		Long num = (long) 70;
		try {
			DepartamentoDTO departamentoGuardado = dep.buscarDepartamento(num);
			System.out.println("DATOS " + departamentoGuardado);
			departamentoGuardado.setNombre("Ciencias");
			dep.actualizar(departamentoGuardado);
			DepartamentoDTO d = dep.findByNombre("Ciencias");
			assertTrue(d.getNombre().equals("Ciencias"));
		} catch (ServiceException e) {
			log.error("error inesperado: " + e.getMessage());
		}
	}

	/**
	 * Busca una lista de departamentos por Manager
	 */
	@Test
	public void buscarDepartmentsDeManager() {
		assertEquals(1, dep.buscarDepartamentosEmpleado((long) 200).size());
	}

	/**
	 * Busca un empleado por Id, si no lo encuentra se produce una ServiceException
	 * y muestra en un log un mensaje de error
	 */
	@Test
	public void buscarEmpleado() {
		long id = 100;
		try {
			EmpleadoDTO empleadoTest = emp.buscarEmpleadoId(id);
			assertEquals(empleadoTest.getFullname(), "King Steven");
		} catch (ServiceException e) {
			log.error("error inesperado: " + e.getMessage());
		}

	}

	/**
	 * Busca una lista de empleados por Manager
	 */
	@Test
	public void buscarEmpleadosDeManager() {
		long id = 108;
		List<EmpleadoDTO> listaEmp = emp.buscarEmpleadosManager(id);
		assertEquals(5, listaEmp.size());
	}

}
