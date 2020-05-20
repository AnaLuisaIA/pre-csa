package com.sadss.csa.dao;

import java.util.List;

import com.sadss.csa.modelo.entidad.Bitacora;
import com.sadss.csa.modelo.generic.IOperations;

public interface BitacoraSistemaDAO extends IOperations<Bitacora>{
	
	public List<Bitacora> getAllBitacoras();
	
	public List<Bitacora> getBitacorasPorBusqueda(Bitacora b);
}
