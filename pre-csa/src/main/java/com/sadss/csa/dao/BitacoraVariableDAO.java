package com.sadss.csa.dao;

import java.util.List;

import com.sadss.csa.controller.beans.BitacoraVariableDTO;
import com.sadss.csa.modelo.entidad.BitacoraVariables;
import com.sadss.csa.modelo.generic.IOperations;

public interface BitacoraVariableDAO extends IOperations<BitacoraVariables> {
	
	/*
	 * Obtiene las Acciones registradass en la bitacora Variable
	 * @return
	 * */
	public List<BitacoraVariableDTO> getRegistros();

}
