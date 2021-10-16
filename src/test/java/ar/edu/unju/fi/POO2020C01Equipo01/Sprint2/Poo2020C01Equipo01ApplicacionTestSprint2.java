package ar.edu.unju.fi.POO2020C01Equipo01.Sprint2;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.AutenticacionUsuarioDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.RolDto;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.AutenticacionUsuarioServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.EmpleadoServiceImp;
import ar.edu.unju.fi.POO2020C01Equipo01.service.impl.RolServiceImp;

@SpringBootTest
@RunWith(JUnitPlatform.class)
public class Poo2020C01Equipo01ApplicacionTestSprint2 {

	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Poo2020C01Equipo01ApplicacionTestSprint2.class);

	@Autowired
	EmpleadoServiceImp emp;

	@Autowired
	RolServiceImp rolServ;

	@Autowired
	AutenticacionUsuarioServiceImp autenticacionServ;

	/**
	 * generamos clave para el empleado 111 que luegos usaremos en el login
	 * comentar si aun no tenemos creada la tabla rol con sus 2 filas.
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		autenticacionServ.generarClave(emp.buscarEmpleadoId(120), "fabian22", 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Este test crea 2 nuevos roles, y devuelve la lista de los roles agregados
	 */
    @Test
	public void agregarRol() {
		try {
			RolDto rolDto = new RolDto();
			rolDto.setId((long) 1);
			rolDto.setNombre("Administrador");
			rolServ.guardar(rolDto);
			rolDto.setId((long) 2);
			rolDto.setNombre("Operador");
			rolServ.guardar(rolDto);
			assertEquals(2, rolServ.obtenerTodos().size());
		} catch (ServiceException e) {
			log.error("Error inesperado " + e.getMessage());
		}
	}

	/**
	 * Este test verifica en la base de datos si un empleado ya tiene clave genereda, si ya tiene devuelve un EmpleadoDTO 
	 * de lo contrario entra a la exception y genera una clave para el empleado
	 * @throws Exception
	 */
	@Test
	public void generarClave() throws Exception {
		AutenticacionUsuarioDTO dto = null;
		try {
			dto = autenticacionServ.generarClave(emp.buscarEmpleadoId(112), "Clave2", 2);
		} catch (Exception e) {
			log.error(e.getCause() +"-"+ e.getMessage());
		}
		assertEquals(dto.getUserName(), "Jose ManuelFI_ACCOUNT");
	}

	/**
	 * La contraseña correcta para que devuelva un dto es fabian El Test representa
	 * a un login de acceso, busca en la base de datos, el usuario y contraseña
	 * enviado por parametro, y si coincide devuelve un EmpleadoDto, si no entra a
	 * la exeption y avisa que el login es incorrecto mediante log.ERROR
	 * 
	 * @throws Exception
	 */
	@Test
	public void login() throws Exception {
		String fullName = null;
		try {
			fullName = autenticacionServ.login("MatthewST_MAN", "fabian22").getFullname();
		} catch (Exception e) {
			log.error("LOGIN INCORRECTO");
		} finally {
			assertEquals(fullName, "Weiss Matthew");
		}
	}
	
}
