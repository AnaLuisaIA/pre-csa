package com.sadss.csa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;

import com.sadss.csa.controller.beans.CalculosImssForm;
import com.sadss.csa.controller.beans.VariablesDTO;
import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.CalculoIMSS;
import com.sadss.csa.modelo.entidad.DatosCarga;
import com.sadss.csa.util.enums.TipoPeriodo;

public interface CalculoIMSSService extends CrudService<CalculoIMSS> {

	/**
	 * Verifica si el perido ya ha sido calculado
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @param periodo
	 * @return true - El periodo existe false - El periodo no está registrado
	 */
	public Boolean periodoExiste(Date fechaInicio, Date fechaFin, TipoPeriodo periodo);

	/**
	 * Descargar archivo de carga de datos para cálculos
	 * 
	 * @param request
	 * @param response
	 */
	public void descargarXLSX(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Mapea las variables necesarias para realizar el cálculo IMSS
	 * 
	 * @param variables Lista de variables que coinciden con las fechas dadas
	 * @return LinkedHashMap con valores de código - valor
	 */
	public LinkedHashMap<String, BigDecimal> extraerVariables(List<VariablesDTO> variables);

	/**
	 * Obtención de datos extra del archivo
	 * 
	 * @param datos Objeto DatosCarga
	 * @param col   Número de columna
	 * @param celda Objeto Celda
	 */
	public void setValoresComplemento(DatosCarga datos, int col, Cell celda);

	/**
	 * Obtención de Bonos incluídos en el archivo
	 * 
	 * @param datos Objeto DatosCarga
	 * @param col   Número de columna
	 * @param celda Objeto Celda
	 */
	public void setValoresBonos(DatosCarga datos, int col, Cell celda);
	
	public List<CalculoIMSS> getRecalcular(Date fechaInicio, Date fechaFin, TipoPeriodo periodo);

	public CalculoIMSS realizarCalculos(CalculosImssForm cif, DatosCarga datos, LinkedHashMap<String, BigDecimal> var,
			String colaborador, BigDecimal dias, BigDecimal sdb, BigDecimal sd_base, int clave);

	public void generarArchivoCalculos(HttpServletRequest request, HttpServletResponse response,
			List<CalculoIMSS> calculos, Date fechaInicio, Date fechaFin, String colaborador);
	
	/*
	 * Metodo para traer los registro del calculo IMSS
	 * */
	public List<CalculoIMSS> getAllCalculo();
	
	/*
	 * Metodo para traer los Registros del Calculos IMMS con filtros
	 * */
	public List<CalculoIMSS> getCalculoIMSSPorBusqueda(CalculoIMSS ci);
	
	/*
	 * Método para extrarer los Colaboradores
	 * **/
	public List<CalculoIMSS> getUsuarios();
	
	/**
	 * Método para extraer las fechas de calculo
	 * */
	public List<CalculoIMSS> getFechaCalculo();
}
