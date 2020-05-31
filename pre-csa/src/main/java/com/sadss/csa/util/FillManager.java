package com.sadss.csa.util;

import java.util.List;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.sadss.csa.modelo.entidad.CalculoIMSS;

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
	
	public static void fillPlantillaCarga(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex) {
		inicioRowIndex += 1;
		
		DataFormat fmt = sheet.getWorkbook().createDataFormat();
		
		XSSFCellStyle bodyCellStyleNum = sheet.getWorkbook().createCellStyle();
		bodyCellStyleNum.setDataFormat(fmt.getFormat("0"));
		bodyCellStyleNum.setWrapText(true);
		
		XSSFCellStyle decimalCellStyle = sheet.getWorkbook().createCellStyle();
		decimalCellStyle.setDataFormat(fmt.getFormat("0.00"));
		decimalCellStyle.setWrapText(true);
		
		//Columna Clave Agente
		sheet.setDefaultColumnStyle(0, bodyCellStyleNum);
		
		//Columna Fecha Alta y Baja
		XSSFCellStyle bodyCellStyleDate = sheet.getWorkbook().createCellStyle();
		bodyCellStyleDate.setDataFormat(fmt.getFormat("dd/mm/yyyy"));
		bodyCellStyleDate.setWrapText(true);
		
		sheet.setDefaultColumnStyle(1, bodyCellStyleDate);
		sheet.setDefaultColumnStyle(2, bodyCellStyleDate);
		
		//Columna Días Laborados a Otro Bono 10
		for(int i = 3; i < 30; i++) {
			sheet.setDefaultColumnStyle(i, decimalCellStyle);
		}
		
	}
	
	public static void fillCalculosIMSS(XSSFSheet sheet, int inicioRowIndex, int inicioColIndex, List<CalculoIMSS> calculos) {
		inicioRowIndex += 4;
		
		DataFormat fmt = sheet.getWorkbook().createDataFormat();
		
		XSSFCellStyle bodyCellStyle = sheet.getWorkbook().createCellStyle();
		bodyCellStyle.setAlignment(HorizontalAlignment.CENTER);
		bodyCellStyle.setWrapText(true);
		bodyCellStyle.setDataFormat(fmt.getFormat("#,##0.00"));
		
		XSSFCellStyle claveAgenteCellStyle = sheet.getWorkbook().createCellStyle();
		claveAgenteCellStyle.setAlignment(HorizontalAlignment.CENTER);
		claveAgenteCellStyle.setWrapText(true);
		claveAgenteCellStyle.setDataFormat(fmt.getFormat("0"));
		
		
		for(int i=inicioRowIndex; i+inicioRowIndex-4 < calculos.size()+4; i++) {
			XSSFRow row = sheet.createRow((short) i);
			
			XSSFCell celda = row.createCell(inicioColIndex+0);
			celda.setCellValue(calculos.get(i-4).getClaveAgente());
			celda.setCellStyle(claveAgenteCellStyle);
			
			celda = row.createCell(inicioColIndex+1);
			celda.setCellValue(calculos.get(i-4).getCuotaFijaP().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+2);
			celda.setCellValue(calculos.get(i-4).getExcedenteP().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+3);
			celda.setCellValue(calculos.get(i-4).getPrestacionesP().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+4);
			celda.setCellValue(calculos.get(i-4).getGastosMedP().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+5);
			celda.setCellValue(calculos.get(i-4).getRTP().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+6);
			celda.setCellValue(calculos.get(i-4).getGuarderiaP().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+7);
			celda.setCellValue(calculos.get(i-4).getInvVidaP().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+8);
			celda.setCellValue(calculos.get(i-4).getTotalPatron().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+9);
			celda.setCellValue(calculos.get(i-4).getExcedenteT().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+10);
			celda.setCellValue(calculos.get(i-4).getPrestacionesT().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+11);
			celda.setCellValue(calculos.get(i-4).getGastosMedicosT().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+12);
			celda.setCellValue(calculos.get(i-4).getInvVidaT().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+13);
			celda.setCellValue(calculos.get(i-4).getTotalTrabajador().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+14);
			celda.setCellValue(calculos.get(i-4).getInfonavitPatron().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+15);
			celda.setCellValue(calculos.get(i-4).getInfonavitTrabajador().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+16);
			celda.setCellValue(calculos.get(i-4).getTotalInfonavit().doubleValue());
			celda.setCellStyle(bodyCellStyle);
			
			celda = row.createCell(inicioColIndex+17);
			celda.setCellValue(calculos.get(i-4).getTotalIMSS().doubleValue());
			celda.setCellStyle(bodyCellStyle);
		}
	}

}
