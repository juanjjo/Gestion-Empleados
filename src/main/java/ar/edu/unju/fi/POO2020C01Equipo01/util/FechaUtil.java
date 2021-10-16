package ar.edu.unju.fi.POO2020C01Equipo01.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtil {

	public static String getDate(long fecha) {
		   Date date = new Date(fecha);
		   SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH mm ss");
		return formatter.format(date);
	}
}
