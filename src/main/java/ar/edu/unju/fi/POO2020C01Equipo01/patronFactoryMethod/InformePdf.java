package ar.edu.unju.fi.POO2020C01Equipo01.patronFactoryMethod;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpLevelDto;
import ar.edu.unju.fi.POO2020C01Equipo01.dto.EmpleadoDTO;
import ar.edu.unju.fi.POO2020C01Equipo01.exceptions.ServiceException;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoLevelManager;
import ar.edu.unju.fi.POO2020C01Equipo01.manager.EmpleadoRegionManager;
import ar.edu.unju.fi.POO2020C01Equipo01.util.FechaUtil;

@Named
public class InformePdf implements FabricaInforme {
	private static Logger log = Logger.getLogger(InformePdf.class);
	private String FILE = PATH_INFORME + "InformesPDF/";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font catFontRed = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD, BaseColor.RED);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font headerTableFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static int idPdf = 0;
	private int idInfPdf;

	public InformePdf() {
		idInfPdf = InformePdf.idPdf++;
	}

	@Override
	public Boolean generarInforme(List<EmpleadoDTO> lista, String descripcion) {
		log.debug("Generando Informe en PDF...");
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(
					FILE + idInfPdf + "_" + FechaUtil.getDate(System.currentTimeMillis()) + ".pdf"));
			document.open();
			addMetaData(document);
			addCabecera(document, descripcion);
			addEmpleados(lista, document);
			document.close();
			log.info("Fin Informe PDF " + idInfPdf + " - creado en :" + FILE);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean generarInforme(EmpleadoRegionManager emp, String descripcion) {
		log.debug("Generando Informe en PDF...");
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(
					FILE + idInfPdf + "_" + FechaUtil.getDate(System.currentTimeMillis()) + ".pdf"));
			document.open();
			document.newPage();
			addMetaData(document);
			addCabecera(document, descripcion);
			crear4tablas(document, emp);
			document.close();
			log.info("Fin Informe PDF " + idInfPdf + " - creado en :" + FILE);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean generarInforme(EmpleadoLevelManager emp, String descripcion) throws ServiceException {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(
					FILE + idInfPdf + "_" + FechaUtil.getDate(System.currentTimeMillis()) + ".pdf"));
			document.open();
			document.newPage();
			addMetaData(document);
			addCabecera(document, descripcion);
			agregarContenido(emp.getLista(), document);
			document.close();
			log.info("Fin Informe PDF " + idInfPdf + " - creado en :" + FILE);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void addMetaData(Document document) {
		document.addTitle("Gestion de Empleados");
		document.addSubject("Empleados");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Equipo01 C01");
		document.addCreator("MAVEN itextpdf");
	}

	private static void addCabecera(Document document, String descripcion) throws DocumentException {
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Informe de Empleados", catFont));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Tipo de informe: " + descripcion, smallBold));
		preface.add(new Paragraph("Fecha y hora de informe: " + FechaUtil.getDate(System.currentTimeMillis())));
		document.add(preface);
	}

	private static void addEmpleados(List<EmpleadoDTO> lista, Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 3);
		document.add(new Paragraph("Lista de Empleados", redFont));
		crearTabla(document, lista);
	}

	private static void crearTabla(Document document, List<EmpleadoDTO> lista) throws DocumentException {
		PdfPTable table = new PdfPTable(5);

		PdfPCell cell = new PdfPCell(new Phrase("Nombre y Apellido", headerTableFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Departamento", headerTableFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Trabajo", headerTableFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Contacto", headerTableFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Salario", headerTableFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		table.setHeaderRows(1);

		for (EmpleadoDTO emp : lista) {
			table.addCell(emp.getFullname());
			table.addCell(emp.getNombreDepartamento());
			table.addCell(emp.getNombreTrabajo());
			table.addCell(emp.geteMail());
			table.addCell(emp.getSalary() + "");
		}
		document.add(table);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public void crear4tablas(Document document, EmpleadoRegionManager emp) {
		try {
			agregarEmpleadosRegion(emp.getListRegion1(), "EMPLEADOS DE EUROPA", document);
			agregarEmpleadosRegion(emp.getListRegion2(), "EMPLEADOS DE AMERICA", document);
			agregarEmpleadosRegion(emp.getListRegion3(), "EMPLEADOS DE ASIA", document);
			agregarEmpleadosRegion(emp.getListRegion4(), "EMPLEADOS DE MEDIO ORIENTE Y AFRICA", document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void agregarEmpleadosRegion(List<EmpleadoDTO> lista, String titulo, Document document) {
		Paragraph preface = new Paragraph();
		preface.add(new Paragraph(titulo, catFontRed));
		addEmptyLine(preface, 1);
		try {
			if (lista != null && lista.size() != 0) {
				document.add(preface);
				crearTabla(document, lista);
			} else {
				preface.add(new Paragraph("No hay empleados en esta region", redFont));
				document.add(preface);
			}
		} catch (Exception e) {
			log.error(e.getCause());
		} finally {
			document.newPage();
		}
	}

	public void agregarContenido(List<EmpLevelDto> lista, Document documento) throws ServiceException {
		Paragraph pref = new Paragraph();
		addEmptyLine(pref, 1);
		pref.add(new Paragraph("Lista de empleados", catFontRed));
		addEmptyLine(pref, 1);
		try {
			documento.add(pref);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < lista.size(); i++) {
			try {
				Paragraph preface = new Paragraph();
				preface.add(new Paragraph(lista.get(i).toString()));
				documento.add(preface);
			} catch (Exception e) {
				throw new ServiceException(e.getMessage());
			}

		}
	}

}
