package ar.edu.unju.POO2020C01Equipo01.enums;

public enum TiposInforme {
	PDF("PDF"),
	EXEL("EXEL");
	
	private String tipo; 
	
	private TiposInforme(String tipo) {
		this.tipo = tipo; 
	}
	
	public String getTipo() {
		return tipo; 
	}
}
