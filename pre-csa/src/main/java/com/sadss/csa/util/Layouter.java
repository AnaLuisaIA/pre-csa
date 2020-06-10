package com.sadss.csa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Contruye el estilo de la plantilla de los archivos Excel requeridos
 * 
 * @author ALIA- Ana Luisa Islas Ávila
 */
public class Layouter {

	/**
	 * Construye la plantilla del calendario (sólo estilos)
	 * 
	 * @param sheet
	 * @param inicioRowIndex
	 * @param inicioColIndex
	 */
	public static void buildReport(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex) {
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);

		buildTitle(sheet, inicioRowIndex, inicioColIndex);
		buildHeaders(sheet, inicioRowIndex, inicioColIndex);
	}

	/**
	 * Se construye el título del calendario
	 * 
	 * @param sheet
	 * @param inicioRowIndex
	 * @param inicioColIndex
	 */
	public static void buildTitle(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex) {
		Font fontTitle = sheet.getWorkbook().createFont();
		fontTitle.setBold(true);
		fontTitle.setFontHeight((short) 280);

		XSSFCellStyle cellStyleTitle = sheet.getWorkbook().createCellStyle();
		cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);
		cellStyleTitle.setWrapText(true);
		cellStyleTitle.setFont(fontTitle);

		XSSFRow rowTitle = sheet.createRow((short) inicioRowIndex);
		rowTitle.setHeight((short) 500);
		XSSFCell cellTitle = rowTitle.createCell(inicioColIndex);
		cellTitle.setCellValue("CALENDARIO ISN");
		cellTitle.setCellStyle(cellStyleTitle);

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
	}

	/**
	 * Construye las cabeceras de cada columna
	 * 
	 * @param sheet
	 * @param inicioRowIndex
	 * @param inicioColIndex
	 */
	public static void buildHeaders(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex) {
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setColor(IndexedColors.BLACK.getIndex());

		XSSFCellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
		headerCellStyle.setAlignment(HorizontalAlignment.LEFT);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);

		XSSFRow rowHeader = sheet.createRow((short) inicioRowIndex + 1);
		rowHeader.setHeight((short) 500);

		int contador = 0;
		String[] cabecerasTitle = { "Semana", "Del", "Al", "Mes", "Trimestre" };

		for (String titulo : cabecerasTitle) {
			XSSFCell celda = rowHeader.createCell(inicioColIndex + contador);
			celda.setCellValue(titulo);
			celda.setCellStyle(headerCellStyle);

			contador++;
		}
	}

	/**
	 * Construye la plantilla del archivo de carga de datos para cálculo
	 * 
	 * @param sheet
	 * @param inicioRowIndex
	 * @param inicioColIndex
	 */
	public static void buildPlantillaCarga(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex) {

		for (int i = 0; i < 30; i++) {
			sheet.setColumnWidth(i, 4000);
		}

		buildCabecerasCarga(sheet, inicioRowIndex, inicioColIndex);
	}

	/**
	 * Construye las cabeceras del archivo de carga
	 * 
	 * @param sheet
	 * @param inicioRowIndex
	 * @param inicioColIndex
	 */
	public static void buildCabecerasCarga(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex) {
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setColor(IndexedColors.BLACK.getIndex());

		XSSFCellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);

		XSSFRow rowHeader = sheet.createRow((short) inicioRowIndex);
		rowHeader.setHeight((short) 600);

		int contador = 0;
		String[] cabecerasTitle = { "Clave Agente", "Fecha Alta", "Fecha Baja", "Días Laborados", "SD", "SD Base",
				"Sueldo", "Aguinaldo", "Vacaciones", "Prima Vacacional", "Reparto de Utilidades", "Indemnización",
				"20 días por año", "Prima de Antigüedad", "Compensación V", "Premios", "Bono", "Bono Lealtad",
				"Bono Digital", "Bono por Traslado", "Otro Bono 1", "Otro Bono 2", "Otro Bono 3", "Otro Bono 4",
				"Otro Bono 5", "Otro Bono 6", "Otro Bono 7", "Otro Bono 8", "Otro Bono 9", "Otro Bono 10" };

		for (String titulo : cabecerasTitle) {
			XSSFCell celda = rowHeader.createCell(inicioColIndex + contador);
			celda.setCellValue(titulo);
			celda.setCellStyle(headerCellStyle);

			contador++;
		}
	}

	public static void buildArchivoSalidaImss(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex, Date fechaInicio,
			Date fechaFin, String colaborador) {

		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 4000);
		sheet.setColumnWidth(14, 4000);
		sheet.setColumnWidth(15, 4000);
		sheet.setColumnWidth(16, 4000);
		sheet.setColumnWidth(17, 4000);

		buildTitleSalidaImss(sheet, inicioRowIndex, inicioColIndex, fechaInicio, fechaFin, colaborador);
		buildHeadersSalidaImss(sheet, inicioRowIndex, inicioColIndex);

	}

	public static void buildTitleSalidaImss(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex, Date fechaInicio,
			Date fechaFin, String colaborador) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		XSSFCellStyle cellStyleTitle = sheet.getWorkbook().createCellStyle();
		cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);
		cellStyleTitle.setWrapText(true);

		XSSFRow rowTitle = sheet.createRow((short) inicioRowIndex);
		rowTitle.setHeight((short) 300);
		XSSFCell cellTitle = rowTitle.createCell(inicioColIndex);
		cellTitle.setCellValue(
				"Periodo del ".concat(sdf.format(fechaInicio).concat(" al ").concat(sdf.format(fechaFin))));
		cellTitle.setCellStyle(cellStyleTitle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

		XSSFCell cellTitle2 = rowTitle.createCell(inicioColIndex + 3);
		cellTitle2.setCellValue("Realizó cálculo: ".concat(colaborador));
		cellTitle2.setCellStyle(cellStyleTitle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));

		XSSFCell cellTitle3 = rowTitle.createCell(inicioColIndex + 6);
		cellTitle3.setCellValue("Fecha de elaboración de reporte: ".concat(sdf.format(new Date())));
		cellTitle3.setCellStyle(cellStyleTitle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 8));
	}

	public static void buildHeadersSalidaImss(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex) {
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setColor(IndexedColors.BLACK.getIndex());

		XSSFCellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);

		XSSFCellStyle headerCellStyle2 = sheet.getWorkbook().createCellStyle();
		headerCellStyle2.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle2.setWrapText(true);
		headerCellStyle2.setFont(font);
		headerCellStyle2.setBorderBottom(BorderStyle.THIN);
		headerCellStyle2.setBorderLeft(BorderStyle.THIN);
		headerCellStyle2.setBorderRight(BorderStyle.THIN);
		headerCellStyle2.setBorderTop(BorderStyle.THIN);

		XSSFRow rowHeader = sheet.createRow((short) inicioRowIndex + 2);
		rowHeader.setHeight((short) 300);

		CellRangeAddress range = new CellRangeAddress(2, 2, 1, 8);
		sheet.addMergedRegion(range);

		XSSFCell celda = rowHeader.createCell(inicioColIndex + 1);
		celda.setCellValue("Cuota Patronal IMSS");
		celda.setCellStyle(headerCellStyle);
		RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, range, sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);
		RegionUtil.setBorderTop(BorderStyle.THIN, range, sheet);

		CellRangeAddress range2 = new CellRangeAddress(2, 2, 9, 13);
		sheet.addMergedRegion(range2);

		XSSFCell celda2 = rowHeader.createCell(inicioColIndex + 9);
		celda2.setCellValue("Cuota Trabajador IMSS");
		celda2.setCellStyle(headerCellStyle);
		RegionUtil.setBorderBottom(BorderStyle.THIN, range2, sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, range2, sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, range2, sheet);
		RegionUtil.setBorderTop(BorderStyle.THIN, range2, sheet);

		XSSFRow rowHeader2 = sheet.createRow((short) inicioRowIndex + 3);
		rowHeader2.setHeight((short) 600);

		int contador = 0;
		String[] cabecerasTitle = { "Clave Agente", "Cuota Fija", "Excedente 3UMA", "Prest. en dinero",
				"Gastos Médicos Pens.", "RT", "Guard G y PS", "Inv y Vida", "Total Patrón", "Excedente 3UMA",
				"Prest. en dinero", "Gastos Médicos Pens.", "Inv y Vida", "Total Trabajador", "INFONAVIT Patrón",
				"INFONAVIT Trabajador", "Total INFONAVIT", "Total IMSS" };

		for (String titulo : cabecerasTitle) {
			XSSFCell celda3 = rowHeader2.createCell(inicioColIndex + contador);
			celda3.setCellValue(titulo);
			celda3.setCellStyle(headerCellStyle2);

			contador++;
		}

	}

	public static void buildArchivoSalidaIsn(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex, Date fechaInicio,
			Date fechaFin, String colaborador) {

		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);

		buildTitleSalidaIsn(sheet, inicioRowIndex, inicioColIndex, fechaInicio, fechaFin, colaborador);
		buildHeadersSalidaIsn(sheet, inicioRowIndex, inicioColIndex);

	}

	public static void buildTitleSalidaIsn(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex, Date fechaInicio,
			Date fechaFin, String colaborador) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		XSSFCellStyle cellStyleTitle = sheet.getWorkbook().createCellStyle();
		cellStyleTitle.setAlignment(HorizontalAlignment.LEFT);
		cellStyleTitle.setWrapText(true);

		XSSFRow rowTitle = sheet.createRow((short) inicioRowIndex);
		rowTitle.setHeight((short) 300);
		XSSFCell cellTitle = rowTitle.createCell(inicioColIndex);
		cellTitle.setCellValue(
				"Periodo del ".concat(sdf.format(fechaInicio).concat(" al ").concat(sdf.format(fechaFin))));
		cellTitle.setCellStyle(cellStyleTitle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

		XSSFCell cellTitle2 = rowTitle.createCell(inicioColIndex + 3);
		cellTitle2.setCellValue("Realizó cálculo: ".concat(colaborador));
		cellTitle2.setCellStyle(cellStyleTitle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));

		XSSFRow rowTitle2 = sheet.createRow((short) inicioRowIndex + 1);
		XSSFCell cellTitle3 = rowTitle2.createCell(inicioColIndex);
		cellTitle3.setCellValue("Fecha de elaboración de reporte: ".concat(sdf.format(new Date())));
		cellTitle3.setCellStyle(cellStyleTitle);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
	}

	public static void buildHeadersSalidaIsn(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex) {
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setColor(IndexedColors.BLACK.getIndex());

		XSSFCellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(BorderStyle.THIN);
		headerCellStyle.setBorderLeft(BorderStyle.THIN);
		headerCellStyle.setBorderRight(BorderStyle.THIN);
		headerCellStyle.setBorderTop(BorderStyle.THIN);

		XSSFRow rowHeader = sheet.createRow((short) inicioRowIndex + 3);
		rowHeader.setHeight((short) 600);

		int contador = 0;
		String[] cabecerasTitle = { "Localidad", "Clave Agente", "Tasa", "Base Gravable", "ISN Mensual",
				"ISN Semanal" };

		for (String titulo : cabecerasTitle) {
			XSSFCell celda = rowHeader.createCell(inicioColIndex + contador);
			celda.setCellValue(titulo);
			celda.setCellStyle(headerCellStyle);

			contador++;
		}
	}
}
