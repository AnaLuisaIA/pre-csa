package com.sadss.csa.service;

import java.util.Date;
import java.util.List;

import com.sadss.csa.controller.beans.BitacoraCalendarioDTO;
import com.sadss.csa.controller.beans.BitacoraVariableDTO;
import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.dao.impl.BitacoraVariableDaoImpl;
import com.sadss.csa.modelo.entidad.BitacoraVariables;
import com.sadss.csa.modelo.entidad.Usuario;


public interface BitacoraVariablesService extends CrudService<BitacoraVariables>{

	/*
	 * Obtiene las acciones Registradas en Bitacora Variable
	 * @reuturn Lista Acciones
	 * */
	public List<BitacoraVariableDTO> getRegistros();
	

}
