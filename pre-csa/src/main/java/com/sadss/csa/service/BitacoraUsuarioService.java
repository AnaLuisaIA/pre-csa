package com.sadss.csa.service;

import java.util.List;

import com.sadss.csa.controller.beans.BitacoraUsuarioDTO;
import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.BitacoraUsuario;

public interface BitacoraUsuarioService extends CrudService<BitacoraUsuario> {

	/**
	 * Obtiene todas las acciones registradas en la bitácora de calendario
	 * @return Lista de acciones
	 */
	public List<BitacoraUsuarioDTO> getRegistros();
}
