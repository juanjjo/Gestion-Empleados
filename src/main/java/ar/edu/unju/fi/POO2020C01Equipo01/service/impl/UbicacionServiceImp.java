package ar.edu.unju.fi.POO2020C01Equipo01.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.POO2020C01Equipo01.dao.UbicacionRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.UbicacionDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Ubicacion;
import ar.edu.unju.fi.POO2020C01Equipo01.service.UbicacionService;

@Service
public class UbicacionServiceImp implements UbicacionService {
	ModelMapper mapper = new ModelMapper();
	@Autowired
	UbicacionRepository locationDao;
	
	@Override
	public UbicacionDTO guardar(UbicacionDTO obj) {
		Ubicacion location = new Ubicacion();
		mapper.map(obj, location);
		locationDao.save(location);
		return obj;
	}

	public Ubicacion BuscarPorID(Long id) {
		return locationDao.findById(id).get();
	}

}
