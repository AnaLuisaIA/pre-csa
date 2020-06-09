package com.sadss.csa.service;

import java.util.List;

import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.CalculoISN;

public interface CalculoIsnService extends CrudService<CalculoISN>{

	/*
	 * Método para consultar los registros de Calculo ISN
	 * **/
	public List<CalculoISN> getAllCalculoISN();
	/*
	 *Método para traer los Registros de Calculos ISN con filtros
	 * **/
	public List<CalculoISN> getCalculosISNPorBusqueda(CalculoISN c);
	
	/**
	 * Método para traer los registros de los agentes.
	 * **/
	public List<CalculoISN> getAllAgente();
	
	/*
	 * Método para traer los registros de las fechas periodo
	 * **/
	public List<CalculoISN> getAllFechaCalculo();
	
	/*
	 * Método para traer los registros de los colaboradores
	 * **/
	public List<CalculoISN> getAllColaborador();
}
