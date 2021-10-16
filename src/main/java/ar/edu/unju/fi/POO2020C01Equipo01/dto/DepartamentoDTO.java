package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Departamento;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Ubicacion;

public class DepartamentoDTO implements Serializable{
    EmpleadoDTOMapper empMapper;
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private long ubicacion;
    private long manager;
	public void map(Departamento dep)
	{
		this.id = dep.getId();
		this.nombre = dep.getNombre();
		if (dep.getManager() != null)
			this.manager = dep.getManager().getId();
		this.ubicacion = dep.getUbicacion().getId();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(long ubicacion) {
		this.ubicacion = ubicacion;
	}
	public long getManager() {
		return manager;
	}
	public void setManager(long manager) {
		this.manager = manager;
	}
	
	public DepartamentoDTO mapper(Departamento dep)
	{
		DepartamentoDTO depDTO = new DepartamentoDTO(); 
		depDTO.setId(dep.getId());
		if(dep.getManager()!=null)
		   depDTO.setManager(dep.getManager().getId());
		depDTO.setNombre(dep.getNombre());
		depDTO.setUbicacion(dep.getUbicacion().getId());
		return depDTO;
	}
	
	public List<DepartamentoDTO> convert(List<Departamento> lista)
	{
	   List<DepartamentoDTO> listaDTO = new ArrayList<DepartamentoDTO>();
	   for(Departamento dep : lista )
	   {
		   listaDTO.add(mapper(dep));
	   }
	   return listaDTO;
	}
}
