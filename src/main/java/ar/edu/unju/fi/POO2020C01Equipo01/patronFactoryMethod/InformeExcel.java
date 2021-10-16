package ar.edu.unju.fi.POO2020C01Equipo01.patronFactoryMethod;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Calendar;
import java.util.Date;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpLevelDto;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoLevelManager;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoRegionManager;
import ar.edu.unju.fi.POO2020C01Equipo01.util.FechaUtil;
@Named
public class InformeExcel implements FabricaInforme {
	private static Logger log = Logger.getLogger(InformeExcel.class);
	
	private  String FILE = PATH_INFORME + "InformesExel/";
	private  String[] header = new String[]{"Nombre y Apellido", "Departamento", "Trabajo", "Contacto", "Salario"};
	private XSSFWorkbook book = new XSSFWorkbook();
	   private static int idExcel = 0;
	   private int idInfExcel;
	   
	    public InformeExcel() {
	        idInfExcel = InformeExcel.idExcel++;
	    }
	
	@Override
	/**
	 * genera el informe en excel solicitado
	 */
	public Boolean generarInforme(List<EmpleadoDTO> lista, String titulo) {
			XSSFSheet hoja = book.createSheet("Hoja1");
	        generarContenido(lista, titulo,hoja,"Lista de Empleados");
	        File excelFile;
	        excelFile = new File( FILE+ idInfExcel+"_"+FechaUtil.getDate(System.currentTimeMillis())+".xlsx");
	        try (FileOutputStream fileOuS = new FileOutputStream(excelFile)) {
	            if (excelFile.exists()) { 
	                excelFile.delete(); 
	            }
	            book.write(fileOuS);
	            fileOuS.flush();
	            fileOuS.close();
	            log.info("Archivo creado correctamente : " + FILE);
	            return true;
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	
	@Override
	public Boolean generarInforme(EmpleadoRegionManager emp, String descripcion) {
		XSSFSheet hoja = book.createSheet("Hoja1");
		generarContenidoByRegiones(emp,descripcion);
		File excelFile;
		excelFile = new File( FILE+ idInfExcel+"_"+FechaUtil.getDate(System.currentTimeMillis())+".xlsx");
        try (FileOutputStream fileOuS = new FileOutputStream(excelFile)){
        	if (excelFile.exists()) { 
                excelFile.delete(); 
            }
        	 book.write(fileOuS);
	         fileOuS.flush();
	         fileOuS.close();
	         log.info("Archivo creado correctamente : " + FILE);
        	return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	
	}
	@Override
	public Boolean generarInforme(EmpleadoLevelManager emp, String descripcion) throws ServiceException {
		XSSFSheet hoja = book.createSheet("Hoja1");
		generarEncabezado(descripcion, hoja);
		generarTablaEmpleadosJerarquica(emp,hoja);
        File excelFile;
        excelFile = new File( FILE+ idInfExcel+"_"+FechaUtil.getDate(System.currentTimeMillis())+".xlsx");
        try (FileOutputStream fileOuS = new FileOutputStream(excelFile)) {
            if (excelFile.exists()) { 
                excelFile.delete(); 
            }
            book.write(fileOuS);
            fileOuS.flush();
            fileOuS.close();
            log.info("Archivo creado correctamente : " + FILE);
            return true;
 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public void generarContenidoByRegiones(EmpleadoRegionManager emp, String descripcion) {
		XSSFSheet hoja1 = book.createSheet("Region1");
		XSSFSheet hoja2 = book.createSheet("Region2");
		XSSFSheet hoja3 = book.createSheet("Region3");
		XSSFSheet hoja4 = book.createSheet("Region4");
		generarContenido(emp.getListRegion1(), descripcion, hoja1, "EUROPA");
		generarTabla(emp.getListRegion2(),hoja2, "AMERICA");
		generarTabla(emp.getListRegion3(),hoja3, "ASIA");
		generarTabla(emp.getListRegion4(),hoja4, "MEDIO ORIENTE Y AFRICA");
	}
	
	/**
	 * genera el contenido del informe con la lista de empleados y un titulo
	 * 
	 * @param lista
	 * @param titulo
	 */
	public void generarContenido(List<EmpleadoDTO> lista, String titulo,XSSFSheet hoja,String nombreTabla) {
		generarEncabezado(titulo,hoja);
		generarTabla(lista, hoja,nombreTabla);
	}
	
	/**
	 * genera la tabla con su nombre y cabezeras
	 * @param lista
	 * @param hoja
	 * @param nombreTabla
	 */
	public void generarTabla(List<EmpleadoDTO> lista,XSSFSheet hoja,String nombreTabla) {
		generarNombreTabla(hoja,nombreTabla);
		generarCabezera(hoja);
		 int i=5;
	        for (EmpleadoDTO emp : lista) {
	            Row row = hoja.createRow(i++);
	            row.createCell(0).setCellValue(emp.getFullname()); 
	            row.createCell(1).setCellValue(emp.getNombreDepartamento());
	            row.createCell(2).setCellValue(emp.getNombreTrabajo());
	            row.createCell(3).setCellValue(emp.geteMail());
	            row.createCell(4).setCellValue(emp.getSalary());
	        }
	        autoAjustarColumnas(hoja);
	}
	/**
	 * genera la tabla para la lista generarquica de empleados
	 * @param emp
	 * @param hoja
	 */
	public void generarTablaEmpleadosJerarquica(EmpleadoLevelManager emp,XSSFSheet hoja) {
		Row row = hoja.createRow(4);
		Cell cell = row.createCell(0);
		cell.setCellValue("      FullName - Cargo");
		cell.setCellStyle(fuenteEncabezados());
		 int i=5;
	        for (EmpLevelDto empDto : emp.getLista()) {
	            Row row1 = hoja.createRow(i++);
	            row1.createCell(0).setCellValue(empDto.toString()); 
	        }
	        autoAjustarColumnas(hoja);
	}
	
	
	/**
	 * generara el nombre de la tabla 
	 * @param titulo
	 */
	public void generarNombreTabla(XSSFSheet hoja, String nombreTabla) {
		hoja.addMergedRegion(new CellRangeAddress(3, 3, 1, 3));
		Row row = hoja.createRow(3);
		Cell cell = row.createCell(1) ;
		cell.setCellValue(nombreTabla);
		cell.setCellStyle(fuenteNombreTabla());
	}
	/**
	 * 
	 * @param titulo
	 * @param hoja
	 */
	public void generarEncabezado(String titulo,XSSFSheet hoja) {        
		generarTitulo(hoja);
		generarDescripcion(titulo, hoja);
		hoja.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
		Row row2 = hoja.createRow(2);
		String fecha = LocalDate.now().toString();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("H:mm:ss");
		String hora1=format.format(LocalTime.now());
		Cell cellTree = row2.createCell(0);
			cellTree.setCellValue("Fecha y hora del informe: "+fecha+"  -  "+hora1);
			cellTree.setCellStyle(fuenteEncabezados());
	}
	
	public void generarDescripcion(String titulo,XSSFSheet hoja) {
		hoja.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));
		Row row1 = hoja.createRow(1);
		Cell cellTwo = row1.createCell(0) ;
		cellTwo.setCellStyle(fuenteEncabezados());
		cellTwo.setCellValue("Tipo de informe: "+titulo);
	}
	
	public void generarTitulo(XSSFSheet hoja) {
		hoja.addMergedRegion(new CellRangeAddress(0, 0, 1, 3));
		Row row = hoja.createRow(0);
		Cell cell = row.createCell(1) ;
		cell.setCellValue("Informe de Empleados");
		cell.setCellStyle(fuenteHeader());
		hoja.autoSizeColumn(3);
	}
	/**
	 * genera la cabezera los titulos de cada columna 
	 */
	public void generarCabezera(XSSFSheet hoja) {
		XSSFRow row = hoja.createRow(4);//se crea las filas
		for (int j = 0; j < header.length; j++) {
   		 Cell cell = row.createCell(j);//Creando la celda de la cabecera en conjunto con la posición
               cell.setCellValue(header[j]);//Añadiendo el contenido de nuestra lista de productos 
               cell.setCellStyle(fuenteEncabezados());

		}
	}
	/**
	 * ajusta las columnas 
	 */
	public void autoAjustarColumnas(XSSFSheet hoja) {
		for(int i = 0; i < header.length; i++) {
            hoja.autoSizeColumn(i);
        }
	}
	
	/**
	 * funcion que genera un estylo de fuente para el header
	 * @return
	 */
	public CellStyle fuenteHeader() {
		Font headerFont = book.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 16);
        headerFont.setColor(IndexedColors.RED.getIndex());
        
        CellStyle headerCellStyle = book.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setFont(headerFont);
        return headerCellStyle;
	}
	public CellStyle fuenteNombreTabla() {
		Font headerFont = book.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.getIndex());
        
        CellStyle headerCellStyle = book.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setFont(headerFont);
        return headerCellStyle;
	}
	/**
	 * fuente para sub titulo
	 * @return
	 */
	public CellStyle fuenteEncabezados() {
		Font headerFont = book.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.getIndex());
        CellStyle headerCellStyle = book.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return headerCellStyle;

	}

}
