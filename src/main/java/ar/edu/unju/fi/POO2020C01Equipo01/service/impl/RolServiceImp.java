package ar.edu.unju.fi.POO2020C01Equipo01.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.POO2020C01Equipo01.dao.RolRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.RolDto;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Rol;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.service.RolService;

@Service
public class RolServiceImp implements RolService{
	ModelMapper mapper = new ModelMapper();
	@Autowired
	RolRepository rolDao;
	
	/**
	 * Este metoodo recibe un RolDto y lo guarda en la base de datos
	 **/
	@Override
	public RolDto guardar(RolDto obj) throws ServiceException {
		try {
			Rol rol = new Rol();
			mapper.map(obj, rol);
			rolDao.save(rol);
			return obj;
		}catch(Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Este metodo lista todos los roles que estan la base de datos
	 */
	@Override
	public List<Rol> obtenerTodos() {
		// TODO Auto-generated method stub
		return rolDao.findAll();
	}
}
