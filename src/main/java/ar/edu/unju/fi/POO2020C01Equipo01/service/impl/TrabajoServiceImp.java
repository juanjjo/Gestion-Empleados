package ar.edu.unju.fi.POO2020C01Equipo01.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.POO2020C01Equipo01.dao.TrabajoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.TrabajoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Trabajo;
import ar.edu.unju.fi.POO2020C01Equipo01.service.TrabajoService;
@Service
public class TrabajoServiceImp implements TrabajoService{
    ModelMapper mapper = new ModelMapper();
    @Autowired
    TrabajoRepository jobDao;
    
	@Override
	public TrabajoDTO guardad(TrabajoDTO obj) {
		Trabajo job = new Trabajo();
		mapper.map(obj, job);
		jobDao.save(job);
		return obj;
	}
}
