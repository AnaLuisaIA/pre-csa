package com.sadss.csa.util;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Se encarga de llenar documento Excel con la información dada
 * 
 * @author ALIA - Ana Luisa Islas Ávila
 */
public class FillManager {
	
	public static void fillReport(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex) {
		inicioRowIndex += 2;
		
		DataFormat fmt = sheet.getWorkbook().createDataFormat();
		
		XSSFCellStyle bodyCellStyleNum = sheet.getWorkbook().createCellStyle();
		bodyCellStyleNum.setDataFormat(fmt.getFormat("0"));
		bodyCellStyleNum.setWrapText(true);
		
		//Columna Semana

		sheet.setDefaultColumnStyle(0, bodyCellStyleNum);
		
		//Columna Del y Al
		XSSFCellStyle bodyCellStyleDate = sheet.getWorkbook().createCellStyle();
		bodyCellStyleDate.setDataFormat(fmt.getFormat("dd/mm/yyyy"));
		bodyCellStyleDate.setWrapText(true);
		
		sheet.setDefaultColumnStyle(1, bodyCellStyleDate);
		sheet.setDefaultColumnStyle(2, bodyCellStyleDate);
		
		//Columna Mes
		sheet.setDefaultColumnStyle(3, bodyCellStyleNum);
		
		//Columna Trimestre
		sheet.setDefaultColumnStyle(4, bodyCellStyleNum);
		

	}

}
