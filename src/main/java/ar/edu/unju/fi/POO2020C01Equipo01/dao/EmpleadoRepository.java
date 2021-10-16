package ar.edu.unju.fi.POO2020C01Equipo01.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Region;
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long>{
	Optional<Empleado> findByNombre(String nombre);
	List<Empleado> findByManagerId(Long id);
		
	@Query(value ="SELECT * from employees e where ((?1 is null or (first_name"
			+ " =cast(?1 AS text)) or (last_name = cast($1 AS text)))) and (e.department_id in ?2) and ( cast(manager_id as text ) like %?3)", nativeQuery = true)
		List<Empleado> buscarOcurrenciaConList(String nombre, Collection<Long> lista,String idManager);
	
	@Query(value ="SELECT * from employees e where ((?1 is null or (first_name"
			+ " =cast(?1 AS text)) or (last_name = cast($1 AS text)))) and ( cast(manager_id as text ) like %?2)", nativeQuery = true)
		List<Empleado> buscarOcurrenciaSinList(String nombre,String idManager);
	
		 @Query(value =" SELECT * from employees where department_id = ?1 and salary > "
		 		+ " (SELECT avg(salary) from employees where department_id=?1) " , nativeQuery = true )	
		 List<Empleado> buscarSalarioMayorAlPromedio( long department_id);
		 
		 @Query(value =" SELECT * from employees where  employee_id in (SELECT "
		 		+ "employee_id FROM job_history	GROUP BY employee_id HAVING COUNT(*)>=?1)", nativeQuery = true )//para probar, con 2 me devuelve 6 registros
		 List<Empleado> buscarEmpleadosCambiaronCargo(long numeroDeVeces);
         
		 List<Empleado> findByDepartamentoUbicacionPaisRegion(Region region);

		 @Query(value = "WITH RECURSIVE a AS ("
				 +" SELECT employee_id, first_name,last_name"
				 + " ,job_id, manager_id, 1 recursion_level "
				 +" FROM employees "
				 +" WHERE employee_id= ?1 "
				 +" UNION ALL "
				 +" SELECT d.employee_id, d.first_name, d.last_name,d.job_id, d.manager_id, a.recursion_level +1 "
				 +" FROM employees d "
				 +" JOIN a ON a.employee_id = d.manager_id )" 
				 +" SELECT * FROM a ", nativeQuery = true)
         public List<Object[]> busquedaGerarquia(long id_manager);
         
}