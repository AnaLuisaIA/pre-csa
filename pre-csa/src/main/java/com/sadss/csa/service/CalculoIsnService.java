package com.sadss.csa.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sadss.csa.controller.beans.CalculoIsnForm;
import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.CalculoISN;
import com.sadss.csa.modelo.entidad.DatosCarga;

public interface CalculoIsnService extends CrudService<CalculoISN> {

	/*
	 * M�todo para consultar los registros de Calculo ISN
	 * **/
	public List<CalculoISN> getAllCalculoISN();
	/*
	 *M�todo para traer los Registros de Calculos ISN con filtros
	 * **/
	public List<CalculoISN> getCalculosISNPorBusqueda(CalculoISN c);
	
	/**
	 * Genera archivo de salida de C�lculo ISN
	 * @param request
	 * @param response
	 * @param calculos Lista de C�lculos ISN
	 * @param fechaInicio Fecha de inicio de la semana
	 * @param fechaFin Fecha de fin de la semana
	 * @param colaborador N�mero de colaborador
	 */
	public void generarArchivoCalculos(HttpServletRequest request, HttpServletResponse response,
			List<CalculoISN> calculos, Date fechaInicio, Date fechaFin, String colaborador);
	
	/*
	 * M�todo para traer los registros de las fechas periodo
	 * **/
	public List<CalculoISN> getAllFechaCalculo();
	
	/*
	 * M�todo para traer los registros de los colaboradores
	 * **/
	public List<CalculoISN> getAllColaborador();
}