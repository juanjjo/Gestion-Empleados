package ar.edu.unju.fi.POO2020C01Equipo01.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class HistorialTrabajoPk implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="employee_id")
	@MapsId("employee_id")
	private Long idEmpleado;
	
	@Column(name = "start_date")
	@MapsId("start_date")
	private LocalDate date;
	
	public HistorialTrabajoPk() {
		// TODO Auto-generated constructor stub
	}
	 @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((idEmpleado == null) ? 0 : idEmpleado.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistorialTrabajoPk other = (HistorialTrabajoPk) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idEmpleado == null) {
			if (other.idEmpleado != null)
				return false;
		} else if (!idEmpleado.equals(other.idEmpleado))
			return false;
		return true;
	}

	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	@Override
	public String toString() {
		return "HistorialTrabajoPk [idEmpleado=" + idEmpleado + ", date=" + date + "]";
	}
	
	
	
}
