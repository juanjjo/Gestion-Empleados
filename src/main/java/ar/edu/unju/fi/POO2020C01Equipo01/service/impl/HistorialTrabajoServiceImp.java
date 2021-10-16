package ar.edu.unju.fi.POO2020C01Equipo01.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.POO2020C01Equipo01.dao.DepartamentoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.HistorialTrabajoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.HistorialTrabajoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Departamento;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.HistorialTrabajo;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.service.HistorialTrabajoService;

@Service
public class HistorialTrabajoServiceImp implements HistorialTrabajoService {

	
	@Autowired
	private HistorialTrabajoRepository historyJobDao;
	@Autowired
	private DepartamentoRepository depDao;
	
	/**
	 * crea un nuevo registro en job_history
	 */
	@Override
	public HistorialTrabajo guardarNuevoHistorial(Empleado empNuevo){
		Departamento dep = new Departamento();
		HistorialTrabajo historyJob = new HistorialTrabajo();
		dep = depDao.findByid(empNuevo.getDepartamento().getId());
		historyJob.getPk().setDate(LocalDate.now());
		historyJob.getPk().setIdEmpleado(empNuevo.getId());
		historyJob.setFechaFinzalizada(null);
		historyJob.setTrabajo(empNuevo.getTrabajo());
		historyJob.setDepartamentoo(dep);
		historyJobDao.save(historyJob);
		return historyJob;
	}

	
	@Override
	public List<HistorialTrabajoDTO> buscarHistorialDeEmpleado(EmpleadoDTO emp) throws ServiceException{
		 HistorialTrabajoDTO histDTO = new HistorialTrabajoDTO();
		 List<HistorialTrabajo> listHistoryJob = new ArrayList<HistorialTrabajo>();
		try {
		listHistoryJob = historyJobDao.findEmpleado(emp.getId());
		List<HistorialTrabajoDTO> histJob = new ArrayList<HistorialTrabajoDTO>();
		histJob = histDTO.convert(listHistoryJob);
		return histJob;
		}
		catch(Exception e){
			throw new ServiceException("El empleado no existe o aun no fue contratado");
		}
	}

	 

}
