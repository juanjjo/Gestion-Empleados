package ar.edu.unju.fi.POO2020C01Equipo01.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.POO2020C01Equipo01.dao.PaisRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.RegionRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.PaisDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Pais;
import ar.edu.unju.fi.POO2020C01Equipo01.service.PaisService;

@Service
public class PaisServiceImp implements PaisService{
    ModelMapper mapper = new ModelMapper();
	@Autowired
    PaisRepository paisDao;
	
	@Autowired
	RegionRepository regionDao;
    
	/**
	 * Este metodo guarda un nuevo pais
	 */
	@Override
	public PaisDTO guardar(PaisDTO obj) {
		Pais country = new Pais();
		mapper.map(obj, country);
		country.setRegion(regionDao.findById(obj.getRegion()).get());
		paisDao.save(country);
		return obj;
	}

}

