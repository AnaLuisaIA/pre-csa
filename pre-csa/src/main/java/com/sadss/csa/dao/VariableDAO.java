package com.sadss.csa.dao;

import java.util.List;

import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.modelo.generic.IOperations;

public interface VariableDAO extends IOperations<Variable>{

	public List<Variable> findVariables();
}
