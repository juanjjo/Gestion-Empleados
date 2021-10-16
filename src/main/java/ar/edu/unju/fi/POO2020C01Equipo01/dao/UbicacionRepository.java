package ar.edu.unju.fi.POO2020C01Equipo01.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Ubicacion;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Ubicacion;
@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion,Long>{

}