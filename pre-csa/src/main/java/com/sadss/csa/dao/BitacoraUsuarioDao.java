package com.sadss.csa.dao;

import java.util.List;

import com.sadss.csa.controller.beans.BitacoraUsuarioDTO;
import com.sadss.csa.modelo.entidad.BitacoraUsuario;
import com.sadss.csa.modelo.generic.IOperations;

public interface BitacoraUsuarioDao extends IOperations<BitacoraUsuario>{

	/**
	 * Obtiene las acciones registradas en la bitácora
	 * @return Lista de acciones
	 */
	public List<BitacoraUsuarioDTO> getRegistros();
}
