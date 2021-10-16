package ar.edu.unju.fi.POO2020C01Equipo01.manager;

import java.util.List;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpLevelDto;

public class EmpleadoLevelManager {
    private List<EmpLevelDto> lista;
    
	public List<EmpLevelDto> getLista() {
		return lista;
	}

	public void setLista(List<EmpLevelDto> lista) {
		this.lista = lista;
	}
    
}
