package ar.edu.unju.fi.POO2020C01Equipo01.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.POO2020C01Equipo01.dao.RegionRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.RegionDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Region;
import ar.edu.unju.fi.POO2020C01Equipo01.service.RegionService;

@Service
public class RegionServiceImp implements RegionService{
    
    ModelMapper mapper = new ModelMapper();
	@Autowired
    RegionRepository regionDao;
    
	/**
	 * Este metodo guarda una nueva region
	 */
	@Override
	public RegionDTO guardar(RegionDTO obj) {
		Region region = new Region();
		mapper.map(obj, region);
		regionDao.save(region);
		return obj;
	}

}
