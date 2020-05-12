package com.sadss.csa.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Escribe el documento Excel en el output stream
 * 
 * @author ALIA - Ana Luisa Islas Ávila
 */
public class Writer {
	
	public static void write(HttpServletResponse response, XSSFSheet sheet) {
		try {
			
			ServletOutputStream outputStream = response.getOutputStream();
			sheet.getWorkbook().write(outputStream);
			outputStream.flush();
			
		} catch (Exception e) {
			System.out.println("Error al generar el archivo Excel");
		}
	}
}
