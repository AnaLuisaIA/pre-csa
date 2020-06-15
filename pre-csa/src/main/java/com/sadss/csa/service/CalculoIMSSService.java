package com.sadss.csa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;

import com.sadss.csa.controller.beans.CalculosImssDTO;
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
	 * @return true - El periodo existe false - El periodo no est� registrado
	 */
	public Boolean periodoExiste(Date fechaInicio, Date fechaFin, TipoPeriodo periodo);

	/**
	 * Descargar archivo de carga de datos para c�lculos
	 * 
	 * @param request
	 * @param response
	 */
	public void descargarXLSX(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Mapea las variables necesarias para realizar el c�lculo IMSS
	 * 
	 * @param variables Lista de variables que coinciden con las fechas dadas
	 * @return LinkedHashMap con valores de c�digo - valor
	 */
	public LinkedHashMap<String, BigDecimal> extraerVariables(List<VariablesDTO> variables);

	/**
	 * Obtenci�n de datos extra del archivo
	 * 
	 * @param datos Objeto DatosCarga
	 * @param col   N�mero de columna
	 * @param celda Objeto Celda
	 */
	public void setValoresComplemento(DatosCarga datos, int col, Cell celda);

	/**
	 * Obtenci�n de Bonos inclu�dos en el archivo
	 * 
	 * @param datos Objeto DatosCarga
	 * @param col   N�mero de columna
	 * @param celda Objeto Celda
	 */
	public void setValoresBonos(DatosCarga datos, int col, Cell celda);
	
	/**
	 * Obtiene los registros que se recalcular�n
	 * @param fechaInicio
	 * @param fechaFin
	 * @param periodo
	 * @return Lista de C�lculos IMSS
	 */
	public List<CalculoIMSS> getRecalcular(Date fechaInicio, Date fechaFin, TipoPeriodo periodo);

	/**
	 * Realiza c�lculo IMSS
	 * @param cif CalculoImssForm
	 * @param datos Fila de datos
	 * @param var Map de Variables
	 * @param colaborador Nombre de colaborador
	 * @param dias N�mero de d�as trabajador
	 * @param salario_diario Salario Diario
	 * @param sd_base Salario Diario Base
	 * @param clave Clave de agente
	 * @return Objeto C�lculoIMSS
	 */
	public CalculoIMSS realizarCalculos(CalculosImssForm cif, DatosCarga datos, LinkedHashMap<String, BigDecimal> var,
			String colaborador, BigDecimal dias, BigDecimal salario_diario, BigDecimal sd_base, int clave);

	/**
	 * Genera el archivo de salida de los c�lculos IMSS realizados
	 * @param request
	 * @param response
	 * @param calculos Lista de c�lculos
	 * @param fechaInicio Fecha de inicio de periodo
	 * @param fechaFin Fecha de fin de periodo
	 * @param colaborador N�mero de colaborador
	 */
	public void generarArchivoCalculos(HttpServletRequest request, HttpServletResponse response,
			List<CalculoIMSS> calculos, Date fechaInicio, Date fechaFin, String colaborador);
	
	/*
	 * Metodo para traer los registro del calculo IMSS
	 * */
	public List<CalculoIMSS> getAllCalculo();
	
	/*
	 * Metodo para traer los Registros del Calculos IMMS con filtros
	 * */
	public List<CalculosImssDTO> getCalculoIMSSPorBusqueda(CalculoIMSS ci);
	
	/*
	 * M�todo para extrarer los Colaboradores
	 * **/
	public List<CalculoIMSS> getUsuarios();
	
	/**
	 * M�todo para extraer las fechas de calculo
	 * */
	public List<CalculoIMSS> getFechaCalculo();
	
	/**
	 * Extrae informaci�n de C�lculo IMSS
	 * @param id Identificador C�lculo
	 * @return CalculoImssDTO
	 */
	public CalculosImssDTO consultarInfoCalculo(Integer id);
}
