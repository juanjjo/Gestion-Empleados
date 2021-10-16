package ar.edu.unju.fi.POO2020C01Equipo01.service;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.UbicacionDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.Ubicacion;


public interface UbicacionService {
    public UbicacionDTO guardar(UbicacionDTO obj);
    public Ubicacion BuscarPorID(Long id);
}
