package com.sadss.csa.dao;

import java.util.List;

import com.sadss.csa.controller.beans.BitacoraTasaDTO;
import com.sadss.csa.modelo.entidad.BitacoraTasas;
import com.sadss.csa.modelo.generic.IOperations;

public interface BitacoraTasaDAO extends IOperations<BitacoraTasas>{

	/*
	 * Obtiene las Acciones Registradas en la Bitacora Tasas sobre nomina
	 * */
	public List<BitacoraTasaDTO> getRegistros();
	
}
