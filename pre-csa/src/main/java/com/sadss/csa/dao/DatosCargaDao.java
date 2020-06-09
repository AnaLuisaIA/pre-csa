package com.sadss.csa.dao;

import java.util.Date;
import java.util.List;

import com.sadss.csa.modelo.entidad.DatosCarga;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.util.enums.TipoPeriodo;

public interface DatosCargaDao extends IOperations<DatosCarga>{
	
	public List<DatosCarga> findDatosByPeriodo(Date fechaInicio, Date fechaFin, TipoPeriodo periodo);
	
}
