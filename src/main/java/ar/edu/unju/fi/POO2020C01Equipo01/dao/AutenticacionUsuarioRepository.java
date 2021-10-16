package ar.edu.unju.fi.POO2020C01Equipo01.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.AutenticacionUsuario;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;

public interface AutenticacionUsuarioRepository extends JpaRepository<AutenticacionUsuario, Long> {
	AutenticacionUsuario findByEmpleado (Empleado empleado);
	public AutenticacionUsuario findByUserName(String username);
    @Query(value = "Select employee_id from user_autentication where user_name=?1", nativeQuery = true)
    Long buscarIdEmpleado(String username);
}
