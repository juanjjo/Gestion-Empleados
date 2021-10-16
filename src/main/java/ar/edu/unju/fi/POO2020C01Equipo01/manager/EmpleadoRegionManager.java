package ar.edu.unju.fi.POO2020C01Equipo01.manager;

import java.util.List;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;

public class EmpleadoRegionManager {
    private List<EmpleadoDTO> listRegion1;
    private List<EmpleadoDTO> listRegion2;
    private List<EmpleadoDTO> listRegion3;
    private List<EmpleadoDTO> listRegion4;
    
	public EmpleadoRegionManager()
	{}
	
	public List<EmpleadoDTO> getListRegion1() {
		return listRegion1;
	}
	public void setListRegion1(List<EmpleadoDTO> listRegion1) {
		this.listRegion1 = listRegion1;
	}
	public List<EmpleadoDTO> getListRegion2() {
		return listRegion2;
	}
	public void setListRegion2(List<EmpleadoDTO> listRegion2) {
		this.listRegion2 = listRegion2;
	}
	public List<EmpleadoDTO> getListRegion3() {
		return listRegion3;
	}
	public void setListRegion3(List<EmpleadoDTO> listRegion3) {
		this.listRegion3 = listRegion3;
	}
	public List<EmpleadoDTO> getListRegion4() {
		return listRegion4;
	}
	public void setListRegion4(List<EmpleadoDTO> listRegion4) {
		this.listRegion4 = listRegion4;
	}
    
    
}
