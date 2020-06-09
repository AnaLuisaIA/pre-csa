package com.sadss.csa.dao;

import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.sadss.csa.modelo.entidad.CalculoISN;
import com.sadss.csa.modelo.generic.IOperations;

public interface calculoIsnDAO extends IOperations<CalculoISN>{

	public List<CalculoISN> getAllCalculoISN();
	
	public List<CalculoISN> getAllCalculoISNPorBusqueda(CalculoISN c);
	
	public List<CalculoISN> getAllAgente();
	
	public List<CalculoISN> getAllFechaCalculo();
	
	public List<CalculoISN> getAllColaborador();
}
