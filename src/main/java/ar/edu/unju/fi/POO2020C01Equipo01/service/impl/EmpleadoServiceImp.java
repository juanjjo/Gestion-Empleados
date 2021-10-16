package ar.edu.unju.fi.POO2020C01Equipo01.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.POO2020C01Equipo01.Poo2020C01Equipo01Application;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.DepartamentoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.EmpleadoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.HistorialTrabajoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.RegionRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dao.TrabajoRepository;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpLevelDto;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTOMapper;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoFullDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoFullDTOMapper;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoModificarDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoModificarDTOMapper;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Departamento;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.HistorialTrabajo;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Trabajo;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoLevelManager;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoRegionManager;
import ar.edu.unju.fi.POO2020C01Equipo01.service.EmpleadoService;

@Service
public class EmpleadoServiceImp implements EmpleadoService {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Poo2020C01Equipo01Application.class);
	private EmpleadoModificarDTOMapper mapperMd= new EmpleadoModificarDTOMapper();
	private EmpleadoDTOMapper mapperEmp = new EmpleadoDTOMapper();
	@Autowired
	EmpleadoRepository employee;
	@Autowired
	DepartamentoRepository depDao;
	@Autowired
	TrabajoRepository traDao;
	@Autowired
	HistorialTrabajoServiceImp historyServiImp;
	@Autowired
	HistorialTrabajoRepository hisTraDao;
	@Autowired
    RegionRepository regionDAO;
	Departamento dep;
	
	private Empleado emp = new Empleado();
	private EmpleadoModificarDTO empMdto = new EmpleadoModificarDTO();
	private List<HistorialTrabajo> listHistorial = new ArrayList<HistorialTrabajo>();
	private Trabajo trabajo = new Trabajo();
	private Trabajo trabajoActual = new Trabajo();
	private HistorialTrabajo ht = new HistorialTrabajo();

	private EmpleadoDTO empDto= new EmpleadoDTO();
	private EmpleadoFullDTOMapper mapperEmpCompleto = new EmpleadoFullDTOMapper();
	private Empleado manager = new Empleado();
	private EmpleadoRegionManager empManager= new EmpleadoRegionManager();
	
	/**
	 * Este metodo busca empleados por id y devuelve un objeto EmpleadoDto
	 */
	@Override
	public EmpleadoDTO buscarEmpleadoId(long id) throws ServiceException{
		try {
			EmpleadoDTO empleadoDTO = new EmpleadoDTO();
			Optional<Empleado> empleado = employee.findById(id);
			empleadoDTO = mapperEmp.map(empleado.get());
			return empleadoDTO;
		}catch(Exception e) {
			throw new ServiceException("NO SE ENCONTRO EL EMPLEADO CON ESE ID");
		}
	}

	/**
	 * Este metodo devuelve una lista con los empleados que esten bajo la
	 * supervision de un Manager
	 * 
	 * @param long
	 */
	public List<EmpleadoDTO> buscarEmpleadosManager(long id){
		List<Empleado> listaEmpleados = employee.findByManagerId(id);	
		return mapperEmp.convertList(listaEmpleados) ;	
}
	
	/**
	 * Este metodo guarda un nuevo empleado y devuelve un objeto EmpleadoDto y agrega un registro en job_history
	 */
	@Override
	public EmpleadoDTO contratarNuevoEmpleado(EmpleadoFullDTO empNuevo) throws ServiceException{
		    emp = new Empleado(); 
		    Trabajo trabajo = new Trabajo();
		    try {
		    	dep = depDao.findByid(empNuevo.getIdDep());
				manager = employee.findById(empNuevo.getIdMan()).get();
				trabajo = traDao.findById(empNuevo.getTitulo()).get();
				emp=mapperEmpCompleto.map(empNuevo, dep, manager,trabajo);
				employee.save(emp);			
				historyServiImp.guardarNuevoHistorial(emp);
				empDto=mapperEmp.map(emp);
			return empDto;
			} catch (Exception e) {
				throw new ServiceException("Envio datos inexistentes para contratar empleado verifique : departamento, Nuevo Empleado, Manager, Trabajo");
			}				
	}

	/**
	 * Este servicio te permite modificar un empleado, si no se desea modificar 
	 * el trabajo, simplemente envie como parametro el mismo ID	del trabajo actual
	 * del empleado 
	 * @param empDto
	 * @param jobId
	 * @return
	 * @throws ServiceException
	 */
	public EmpleadoModificarDTO modificarEmpleadoo(EmpleadoModificarDTO empDto, String jobId) throws ServiceException{
		validarDatos(empDto, jobId);
		String trabActual = employee.findById(empDto.getId()).get().getTrabajo().getId();
		if(trabActual.equals(trabajo.getId())) 
		   try {
			mapAndSave();
		   }
		   catch(Exception e){
			   throw new ServiceException(e.getMessage());
		   }
		else
		   { try {
			 modificarJobEmpleado();
		     }
		     catch (Exception e) {
				throw new ServiceException(e.getMessage());
			}
		   }
		return mapperMd.map(emp,trabajo);
	}
	
	public void validarDatos(EmpleadoModificarDTO empDto, String jobId) throws ServiceException
	{ double salario;
	try {
    	  emp = employee.findById(empDto.getId()).get();
		  trabajoActual = traDao.findById(emp.getTrabajo().getId()).get();
		  trabajo = traDao.findById(jobId).get();
		  } catch (Exception e) {
				throw new ServiceException("El empleado no fue contratado aun, o el trabajo enviado no existe");
		  }
	emp = mapperMd.mapEmpleado(emp, empDto);
	salario = emp.getSalary();
	if(! (salario>=trabajo.getSalarioMin() && salario<=trabajo.getSalarioMax()))
  	    {
  		 throw new ServiceException("El salario" + salario +" no esta en el rango permitido" + trabajo.getSalarioMin() + " - " + trabajo.getSalarioMax());
  		}
	}
	

	/**
	 * El metodo guarda un empleado en la base de datos.
	 * @param obj
	 */
	protected void guardar(Empleado obj) {
		employee.save(obj);
	}
	
	@Override
	public List<EmpleadoDTO> busquedaOcurrencia(String nombre, List<Long> listDep, Long idManager) {
		List<Empleado> lis = new ArrayList<Empleado>();
		String manager ="";
		if(idManager != null)
			manager = idManager +"";
		if(listDep!=null)
		  lis = employee.buscarOcurrenciaConList(nombre, listDep, manager);
		else
		  lis = employee.buscarOcurrenciaSinList(nombre, manager);
		return mapperEmp.convertList(lis);
	}
	
	/**
	 * Este metodo asigna al objeto empleado, los nuevos datos que desea modificar 
	 * y lo actualiza en la BD
	 */
	private void mapAndSave()
	{
		emp=mapperMd.mapEmpleado(emp, empMdto);
	     emp=employee.save(emp);
	}

	/**
	 * Este metodo modifica el empleado, teniendo en cuenta los casos
	 * en que fecha finalizada = null o fecha Finalizada distinto de null
	 * Es de tipo privada por que se concatena con el servicio en donde
	 * podemos modificar el empleado, modificando o no el Trabajo
	 */
	private void modificarJobEmpleado() 
	{ listHistorial = hisTraDao.findByEmpleadoOrderByDate(emp);
	  ht = listHistorial.get(listHistorial.size()-1);
	  if(ht.getFechaFinzalizada()==null)
  	  { ht.setFechaFinzalizada(LocalDate.now());
  	    hisTraDao.save(ht);//modificamos el historial
  	  }
	  emp.setTrabajo(trabajo);
	  historyServiImp.guardarNuevoHistorial(emp);
	  mapAndSave();
	}


	@Override
	public List<EmpleadoDTO> buscarEmpleadosCambioJob(long cantidadVeces) {
		return mapperEmp.convertList(employee.buscarEmpleadosCambiaronCargo(cantidadVeces));
	}


	@Override
	public List<EmpleadoDTO> buscarEmpleadosSalarioMayorPromedio(long department_id) {
		return mapperEmp.convertList(employee.buscarSalarioMayorAlPromedio(department_id));
	}


	@Override
	public EmpleadoRegionManager buscarEmpleadosAgrupadosRegion() {
		List<EmpleadoDTO> listDTO = new ArrayList<EmpleadoDTO>();
		listDTO = mapperEmp.convertList(employee.findByDepartamentoUbicacionPaisRegion(regionDAO.findById((long)1).get()));
		empManager.setListRegion1(listDTO);
		listDTO = mapperEmp.convertList(employee.findByDepartamentoUbicacionPaisRegion(regionDAO.findById((long)2).get()));
		empManager.setListRegion2(listDTO);
		listDTO=null;
		listDTO = mapperEmp.convertList(employee.findByDepartamentoUbicacionPaisRegion(regionDAO.findById((long)3).get()));
		if(listDTO!=null)
		empManager.setListRegion3(listDTO);
		listDTO=null;
		listDTO = mapperEmp.convertList(employee.findByDepartamentoUbicacionPaisRegion(regionDAO.getOne((long)4)));
		if(listDTO!=null)
		empManager.setListRegion4(listDTO);
		return empManager;
	}


	@Override
	public EmpleadoLevelManager busquedaDesdeManager(long idManager) {
		List<Object[]> listObj = employee.busquedaGerarquia(idManager);
		List<EmpLevelDto> listEmpLevel = new ArrayList<EmpLevelDto>();
		EmpleadoLevelManager empManager = new EmpleadoLevelManager();
		for(Object[] obj : listObj)
		{	EmpLevelDto empLevel = new EmpLevelDto();
			empLevel.map(obj);
			listEmpLevel.add(empLevel);
		}
		empManager.setLista(listEmpLevel);
		return empManager;
	}
}

