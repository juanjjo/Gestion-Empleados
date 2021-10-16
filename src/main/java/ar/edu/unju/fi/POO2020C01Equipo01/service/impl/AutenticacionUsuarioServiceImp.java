package ar.edu.unju.fi.POO2020C01Equipo01.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.AutenticacionUsuarioRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.EmpleadoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.RolRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.AutenticacionUsuarioDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTOMapper;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.AutenticacionUsuario;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Rol;
import ar.edu.unju.fi.POO2020C01Equipo01.service.AutenticacionUsuarioService;
import ar.edu.unju.fi.POO2020C01Equipo01.util.EmcriptacionMd5Util;

@Service
public class AutenticacionUsuarioServiceImp implements AutenticacionUsuarioService {

	private ModelMapper mapperr = new ModelMapper();
	private EmpleadoDTOMapper mapper = new EmpleadoDTOMapper();
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private AutenticacionUsuarioRepository AutenticacionDao;

	@Autowired
	private RolRepository rolDao;

	private AutenticacionUsuario userAutenticacion;
	private EmcriptacionMd5Util util;

	private Empleado empleado;

	@Override
	public AutenticacionUsuarioDTO guardar(AutenticacionUsuarioDTO obj) {
		AutenticacionUsuario autUser = new AutenticacionUsuario();
		mapperr.map(obj, autUser);
		AutenticacionDao.save(autUser);
		return obj;
	}

	/**
	 * Este metodo genera una clave si un empleado no la posee
	 * 
	 * @param empleadoDTO
	 * @param cadena
	 * @param rolId
	 * @return
	 */
	public AutenticacionUsuarioDTO generarClave(EmpleadoDTO empleadoDTO, String cadena, long rolId) {
		Rol rol = rolDao.getOne(rolId);
		empleado = empleadoRepository.findById(empleadoDTO.getId()).get();
		userAutenticacion = AutenticacionDao.findByEmpleado(empleado);
		AutenticacionUsuarioDTO autDto = new AutenticacionUsuarioDTO();
		if (userAutenticacion == null) {
			userAutenticacion = cargarUserAutenticacion(empleado, rol);
			String encript = util.ecnode(cadena);
			userAutenticacion.setPassword(encript);
			empleado.setAutenticacion(userAutenticacion);
			guardarActualizar(userAutenticacion);
			mapperr.map(userAutenticacion, autDto);
			return autDto;
		} else
			return null;
	}

	/**
	 * Este metodo devuelve un AutenticacionUsuario, lo carga con los datos del
	 * empleado recibido
	 * 
	 * @param usu
	 * @param rol
	 * @return
	 */
	public AutenticacionUsuario cargarUserAutenticacion(Empleado usu, Rol rol) {
		AutenticacionUsuario usuario = new AutenticacionUsuario();
		usuario.setEmpleado(usu);
		usuario.setUserName(usu.getNombre() + usu.getTrabajo().getId());
		usuario.setActive(true);
		usuario.setRol(rol);
		return usuario;
	}

	@Override
	public void guardarActualizar(AutenticacionUsuario obj) {
		AutenticacionDao.save(obj);
	}

	/**
	 * Recibe un username y una contraseña, si el login es incorrecto retorna null
	 */
	@Override
	public EmpleadoDTO login(String username, String password) {
		userAutenticacion = AutenticacionDao.findByUserName(username);
		if (userAutenticacion != null) {
			if (userAutenticacion.getPassword().equals(util.ecnode(password))) {
				long id = AutenticacionDao.buscarIdEmpleado(username);
				EmpleadoDTO empDto = mapper.map(empleadoRepository.findById(id).get());
				return empDto;
			} else
				return null;
		} else
			return null;
	}

}
