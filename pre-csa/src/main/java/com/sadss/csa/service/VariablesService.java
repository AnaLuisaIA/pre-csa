package com.sadss.csa.service;

import java.util.Date;
import java.util.List;

import com.sadss.csa.controller.beans.VariablesForm;
import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.Variable;


public interface VariablesService  extends CrudService<Variable>{
	
	
	/**
	 *Guarda registro de creación / actualizacion de Variables en Bitácora
	 *@param usuario (Usuario que realiza la acción) 
	 *@param accion (Acción que realizo el usuario)
	 
	
	public void guardarBitacoraVariables(String usuarios, String accion);*/
	
	/**
	 * Guarda Registros de Variables
	 *
	public void saveVariables (Variable variable); */
	
	/**
	 * Lista de variables
	 * */
	public List<Variable> findVariables();
	
	public PeriodoVariable findVariablesID(Integer id);
	
	
	public Variable updateVariable(int id);
	
	/*
	 * Metodo Registro bitacora
	 * */
	
	public void registrarAccionBitacora(String accion, Date fecha,String justificacion, String user);
}
