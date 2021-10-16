package ar.edu.unju.fi.POO2020C01Equipo01.service;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.AutenticacionUsuarioDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.entity.AutenticacionUsuario;

public interface AutenticacionUsuarioService {
    public AutenticacionUsuarioDTO guardar(AutenticacionUsuarioDTO obj);
    public void guardarActualizar(AutenticacionUsuario obj);
    public EmpleadoDTO login(String username, String password);
}
