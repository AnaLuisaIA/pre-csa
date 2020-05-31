package com.sadss.csa.dao;

import java.util.Date;
import java.util.List;

import com.sadss.csa.controller.beans.VariablesDTO;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.modelo.generic.IOperations;



public interface VariableDAO extends IOperations<Variable>{

	public List<Variable> findVariables();

	public Variable updateVariable(int id);
	
	public List<PeriodoVariable> getPeriodos();
	
	public List<VariablesDTO> getVariablesCalculo(Date fechaAplicacion, Date fechaTermino);

}
