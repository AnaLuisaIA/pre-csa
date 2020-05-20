package com.sadss.csa.service;

import java.util.Date;
import java.util.List;

import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.Bitacora;
import com.sadss.csa.modelo.entidad.Usuario;

public interface BitacoraSistemaService extends CrudService<Bitacora>{

	public List<Bitacora> getAllBitacoras();
	
	public List<Bitacora> getBitacorasPorBusqueda(Bitacora b);
	
	public void guardarRegistroAccion(String accion, Date fechas, Usuario user);
}
