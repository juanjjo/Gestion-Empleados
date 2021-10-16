package ar.edu.unju.fi.POO2020C01Equipo01.dao;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.HistorialTrabajo;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.HistorialTrabajoPk;
@Repository
public interface HistorialTrabajoRepository extends JpaRepository<HistorialTrabajo, HistorialTrabajoPk>{
	List<HistorialTrabajo> findByEmpleadoOrderByDate(Empleado emp);
	HistorialTrabajo findByDate(LocalDate date);
	@Query(value =" SELECT * FROM job_history u WHERE"
			+ " u.employee_id = ?1", nativeQuery = true)
	List<HistorialTrabajo> findEmpleado(Long empleado);
}
