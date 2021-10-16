package ar.edu.unju.fi.POO2020C01Equipo01.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.POO2020C01Equipo01.entity.Departamento;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Empleado;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.HistorialTrabajo;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.HistorialTrabajoPk;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Trabajo;

public class HistorialTrabajoDTO implements Serializable{

	private HistorialTrabajoPk pk;
	private LocalDate fechaFinalizada;
	private String trabajo;
	private String departamento;
	private String empleado;
	
	public HistorialTrabajoPk getPk() {
		return pk;
	}
	public void setPk(HistorialTrabajoPk pk) {
		this.pk = pk;
	}
	public LocalDate getFechaFinalizada() {
		return fechaFinalizada;
	}
	public void setFechaFinalizada(LocalDate fechaFinalizada) {
		this.fechaFinalizada = fechaFinalizada;
	}
	public String getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	@SuppressWarnings("finally")
	public HistorialTrabajoDTO map (HistorialTrabajo hist)
	{
		HistorialTrabajoDTO histJobDTO = new HistorialTrabajoDTO();
		histJobDTO.setDepartamento(hist.getDepartamentoo().getId()+"");
		try {
		histJobDTO.setFechaFinalizada(hist.getFechaFinzalizada());
		}
		catch (Exception e) {
			histJobDTO.setFechaFinalizada(null);
		}
		finally {
		histJobDTO.setTrabajo(hist.getTrabajo().getId());
		histJobDTO.setEmpleado(hist.getEmpleado().getNombre()+" " + hist.getEmpleado().getApellido());
		return histJobDTO;
		}
   }
	
	public List<HistorialTrabajoDTO> convert(List<HistorialTrabajo> lista)
	{
		 List<HistorialTrabajoDTO> listaDTO = new ArrayList<HistorialTrabajoDTO>();
		for(HistorialTrabajo hist : lista)
		  {
			listaDTO.add(map(hist));
		  }
		return listaDTO;
	}
}
