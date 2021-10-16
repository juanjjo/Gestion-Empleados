package ar.edu.unju.fi.POO2020C01Equipo01.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Trabajo;
@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, String>{
	Optional<Trabajo> findByTitulo(String titulo);
}
