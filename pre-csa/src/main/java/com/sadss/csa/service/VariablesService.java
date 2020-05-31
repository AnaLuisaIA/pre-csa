package com.sadss.csa.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.sadss.csa.controller.beans.VariablesDTO;
import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.Variable;


public interface VariablesService  extends CrudService<Variable>{
	
	/**
	 * Lista de variables
	 * */
	public List<Variable> findVariables();
	
	public Variable updateVariable(int id);
	
	public void registrarAccionBitacora(String accion, Date fecha,String justificacion, String user);
	
	public List<PeriodoVariable> getPeriodos() throws ParseException;

	public List<VariablesDTO> getVariablesCalculo(Date fechaAplicacion, Date fechaTermino);
	
}
