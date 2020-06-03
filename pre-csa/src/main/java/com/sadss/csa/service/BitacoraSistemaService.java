package com.sadss.csa.service;

import java.util.List;

import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.Bitacora;

public interface BitacoraSistemaService extends CrudService<Bitacora>{

	public List<Bitacora> getAllBitacoras();
	
	public List<Bitacora> getBitacorasPorBusqueda(Bitacora b);
	
}
