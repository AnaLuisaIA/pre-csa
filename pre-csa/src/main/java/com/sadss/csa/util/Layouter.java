package com.sadss.csa.util;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
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

		sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
	}
	
	
	/**
	 * Construye las cabeceras de cada columna
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
		String[] cabecerasTitle = {"Semana", "Del", "Al", "Mes", "Trimestre"};
		
		for(String titulo: cabecerasTitle) {
			XSSFCell celda = rowHeader.createCell(inicioColIndex + contador);
			celda.setCellValue(titulo);
			celda.setCellStyle(headerCellStyle);
			
			contador++;
		}
	}
}
