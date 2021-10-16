package ar.edu.unju.fi.POO2020C01Equipo01.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.POO2020C01Equipo01.dao.DepartamentoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.EmpleadoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.UbicacionRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.DepartamentoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Departamento;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.service.DepartmentService;

@Service
public class DepartamentServiceImp implements DepartmentService {

	private ModelMapper mapper = new ModelMapper();
	@Autowired
	DepartamentoRepository departmentRepo;

	@Autowired
	UbicacionRepository ubicRepo;
	@Autowired
	EmpleadoRepository empRepo;

	/**
	 * EN este metodo el jpa.save reconoce que ya existe un department con ese id
	 * por lo tanto procede a modificarlo, no a guardar un nuevo registro.
	 */
	@Override
	public DepartamentoDTO actualizar(DepartamentoDTO d) {
		Departamento departamento = new Departamento();
		departamento.setId(d.getId());
		departamento.setNombre(d.getNombre());
		departamento.setManager(empRepo.findById(d.getManager()).get());
		departamento.setUbicacion(ubicRepo.findById(d.getUbicacion()).get());
		departmentRepo.save(departamento);
		return d;
	}

	/**
	 * Busca un departamento por nombre y devuelve un  objeto DepartamentoDto
	 */
	@Override
	public DepartamentoDTO findByNombre(String nombre) {
		Departamento departamento = new Departamento();
		DepartamentoDTO departDTO = new DepartamentoDTO();
		departamento = departmentRepo.findByNombre(nombre);
		departDTO.map(departamento);
		return departDTO;
	}

	/**
	 * Busca un departamento por id y devuelve un objeto DepartamentoDto
	 */
	@Override
	public DepartamentoDTO buscarDepartamento(Long id) throws ServiceException {
		Departamento departamento = new Departamento();
		DepartamentoDTO departamentoDto = new DepartamentoDTO();
		try {
			departamento = departmentRepo.findByid(id);
			departamentoDto.map(departamento);
			return departamentoDto;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Este metodo devuelve una lista de departamentos que estan bajo la supervision
	 * de un empleado
	 */
	@Override
	public List<DepartamentoDTO> buscarDepartamentosEmpleado(Long id) {
		List<DepartamentoDTO> departamentosDtoList = new ArrayList<DepartamentoDTO>();
		List<Departamento> listaDepartamentos = departmentRepo.findByManagerId(id);
		DepartamentoDTO departamentoDTO = new DepartamentoDTO();
		for (Departamento departamento : listaDepartamentos) {
			departamentoDTO.map(departamento);
			departamentosDtoList.add(departamentoDTO);
		}
		return departamentosDtoList;
	}

	@SuppressWarnings("finally")
	@Override
	public List<DepartamentoDTO> buscarSegunParametros(String nombreDep, Long id) throws ServiceException {
		List<Departamento> listDep = new ArrayList<Departamento>();
		DepartamentoDTO departamentoDTO = new DepartamentoDTO();
		try {
			listDep = departmentRepo.findByNombreLikeAndUbicacion("%" + nombreDep + "%", ubicRepo.findById(id).get());
		} catch (Exception e) {
			throw new ServiceException("No existe una Ubicacion con ese ID");
		} finally {
			return departamentoDTO.convert(listDep);
		}
	}
}
