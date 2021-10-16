package ar.edu.unju.fi.POO2020C01Equipo01.service.impl;
import java.util.List;

import ar.edu.unju.POO2020C01Equipo01.enums.TiposInforme;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.patronFactoryMethod.FabricaInforme;
import ar.edu.unju.fi.POO2020C01Equipo01.patronFactoryMethod.InformeExcel;
import ar.edu.unju.fi.POO2020C01Equipo01.patronFactoryMethod.InformePdf;
import ar.edu.unju.fi.POO2020C01Equipo01.service.InformeFactoryMethodService;


public class InformeFactoryMethodServiceImp implements InformeFactoryMethodService {
	
	@Override
	public FabricaInforme getInstance(TiposInforme tipo) {
		if (tipo.getTipo().equals("EXEL"))
		   return new InformeExcel(); 
		else
			return new InformePdf();
	}

}
