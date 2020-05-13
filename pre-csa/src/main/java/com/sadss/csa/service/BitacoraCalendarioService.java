package com.sadss.csa.service;

import java.util.List;

import com.sadss.csa.controller.beans.BitacoraCalendarioDTO;
import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.BitacoraCalendario;

public interface BitacoraCalendarioService extends CrudService<BitacoraCalendario> {

	/**
	 * Obtiene todas las acciones registradas en la bitácora de calendario
	 * @return Lista de acciones
	 */
	public List<BitacoraCalendarioDTO> getRegistros();
}
