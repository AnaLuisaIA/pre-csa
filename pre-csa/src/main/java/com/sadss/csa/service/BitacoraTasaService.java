package com.sadss.csa.service;

import java.util.List;

import com.sadss.csa.controller.beans.BitacoraTasaDTO;
import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.BitacoraTasas;

public interface BitacoraTasaService  extends CrudService<BitacoraTasas>{

	/*
	 * Obtiene las Acciones Registrada en Bitacora Tasas Sobre Nomina
	 * */
	public List<BitacoraTasaDTO> getRegistros();
}
