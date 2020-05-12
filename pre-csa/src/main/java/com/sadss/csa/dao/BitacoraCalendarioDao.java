package com.sadss.csa.dao;

import java.util.List;

import com.sadss.csa.controller.beans.BitacoraCalendarioDTO;
import com.sadss.csa.modelo.entidad.BitacoraCalendario;
import com.sadss.csa.modelo.generic.IOperations;

public interface BitacoraCalendarioDao extends IOperations<BitacoraCalendario>{

	/**
	 * Obtiene las acciones registradas en la bitácora
	 * @return Lista de acciones
	 */
	public List<BitacoraCalendarioDTO> getRegistros();
}
