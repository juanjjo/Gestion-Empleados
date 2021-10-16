package ar.edu.unju.fi.POO2020C01Equipo01.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Departamento;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Ubicacion;



@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Long>{
	Departamento findByNombre(String nombre);
	List<Departamento> findByManagerId(Long idManager);
	Departamento findByid(Long id);
	List<Departamento> findByNombreLikeAndUbicacion(String nombreDepartamento, Ubicacion id);
}