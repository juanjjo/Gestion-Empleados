package ar.edu.unju.fi.POO2020C01Equipo01.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Region;
@Repository
public interface RegionRepository extends JpaRepository<Region,Long>{

}
