package ar.edu.unju.fi.POO2020C01Equipo01.dto;

public class EmpLevelDto {
	private String name;
	private String lastName;
	private String trabajo_id;
	private int level;
	//areglo 0 sin espacios ya que nunca lo recibiremos del mapeo
	private String[] espacios = { "        ","", "  ", "    ", "      ", "        " };

	public void map(Object[] param)
	{
		this.name  = (String) param[1];
		this.lastName = (String) param[2];
		this.trabajo_id = (String) param[3];
		this.level =(int) param[5];
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTrabajo_id() {
		return trabajo_id;
	}

	public void setTrabajo_id(String trabajo_id) {
		this.trabajo_id = trabajo_id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return espacios[0] + espacios[level] + lastName + ", " + name + " - " + trabajo_id;
	}

}
