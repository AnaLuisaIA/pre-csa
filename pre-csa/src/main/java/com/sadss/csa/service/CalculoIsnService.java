package com.sadss.csa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sadss.csa.controller.beans.CalculoIsnDTO;
import com.sadss.csa.controller.beans.CalculoIsnForm;
import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.CalculoISN;
import com.sadss.csa.modelo.entidad.DatosCarga;

public interface CalculoIsnService extends CrudService<CalculoISN> {

	/*
	 * Método para consultar los registros de Calculo ISN
	 **/
	public List<CalculoISN> getAllCalculoISN();

	/*
	 * Método para traer los Registros de Calculos ISN con filtros
	 **/
	public List<CalculoIsnDTO> getCalculosISNPorBusqueda(CalculoISN c);

	/**
	 * Genera archivo de salida de Cálculo ISN
	 * 
	 * @param request
	 * @param response
	 * @param calculos    Lista de Cálculos ISN
	 * @param fechaInicio Fecha de inicio de la semana
	 * @param fechaFin    Fecha de fin de la semana
	 * @param colaborador Número de colaborador
	 */
	public void generarArchivoCalculos(HttpServletRequest request, HttpServletResponse response,
			List<CalculoISN> calculos, Date fechaInicio, Date fechaFin, String colaborador);

	public CalculoISN realizarCalculos(CalculoIsnForm cif, Integer n_semanas, DatosCarga d, String colaborador,
			BigDecimal base_gravable);

	public List<CalculoISN> realizarCalculosTAP(CalculoIsnForm cif, Integer n_semanas, List<DatosCarga> datos,
			String colaborador, BigDecimal suma_baseG, List<CalculoISN> calculosIsn);

	/*
	 * Método para traer los registros de las fechas periodo
	 **/
	public List<CalculoISN> getAllFechaCalculo();

	/*
	 * Método para traer los registros de los colaboradores
	 **/
	public List<CalculoISN> getAllColaborador();

	public List<CalculoISN> getAllAgente();
	
	public CalculoIsnDTO consultarInfoCalculo(Integer id, String mes);
}
