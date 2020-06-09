package com.sadss.csa.service;

import java.util.List;

import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.CalculoISN;

public interface CalculoIsnService extends CrudService<CalculoISN>{

	/*
	 * M�todo para consultar los registros de Calculo ISN
	 * **/
	public List<CalculoISN> getAllCalculoISN();
	/*
	 *M�todo para traer los Registros de Calculos ISN con filtros
	 * **/
	public List<CalculoISN> getCalculosISNPorBusqueda(CalculoISN c);
	
	/**
	 * M�todo para traer los registros de los agentes.
	 * **/
	public List<CalculoISN> getAllAgente();
	
	/*
	 * M�todo para traer los registros de las fechas periodo
	 * **/
	public List<CalculoISN> getAllFechaCalculo();
	
	/*
	 * M�todo para traer los registros de los colaboradores
	 * **/
	public List<CalculoISN> getAllColaborador();
}
