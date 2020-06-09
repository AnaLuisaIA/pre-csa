package com.sadss.csa.dao;

import java.util.Date;
import java.util.List;

import com.sadss.csa.controller.beans.CalculosImssDTO;
import com.sadss.csa.modelo.entidad.CalculoIMSS;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.util.enums.TipoPeriodo;

public interface CalculoIMSSDao extends IOperations<CalculoIMSS> {

	public Boolean periodoExiste(Date fechaInicio, Date fechaFin, TipoPeriodo periodo);
	
	public List<CalculoIMSS> getRecalcular(Date fechaInicio, Date fechaFin, TipoPeriodo periodo);
	
	public List<CalculoIMSS> getAllCalculo();
	
	public List<CalculoIMSS> getCalculoIMSSPorBusqueda(CalculoIMSS ci);
	
	public List<CalculoIMSS> getUsuarios();
	
	public List<CalculoIMSS> getFechaCalculo();
}
