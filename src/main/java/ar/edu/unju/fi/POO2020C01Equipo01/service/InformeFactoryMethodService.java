package ar.edu.unju.fi.POO2020C01Equipo01.service;

import java.util.List;

import ar.edu.unju.POO2020C01Equipo01.enums.TiposInforme;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.patronFactoryMethod.FabricaInforme;

public interface InformeFactoryMethodService {
	FabricaInforme getInstance(TiposInforme tipo);
}