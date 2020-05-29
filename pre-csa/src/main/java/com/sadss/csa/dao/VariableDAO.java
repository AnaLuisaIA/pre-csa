package com.sadss.csa.dao;

import java.util.List;

import com.sadss.csa.controller.beans.VariablesForm;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.modelo.generic.IOperations;



public interface VariableDAO extends IOperations<Variable>{

	public List<Variable> findVariables();

	public PeriodoVariable findVariablesID(Integer id);
	
	public Variable updateVariable(int id);

}
