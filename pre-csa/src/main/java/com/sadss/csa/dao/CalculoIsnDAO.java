package com.sadss.csa.dao;

import java.util.List;

import com.sadss.csa.controller.beans.CalculoIsnDTO;
import com.sadss.csa.modelo.entidad.CalculoISN;
import com.sadss.csa.modelo.generic.IOperations;

public interface CalculoIsnDAO extends IOperations<CalculoISN>{

	public List<CalculoISN> getAllCalculoISN();
	
	public List<CalculoIsnDTO> getAllCalculoISNPorBusqueda(CalculoISN c);
	
	public List<CalculoISN> getAllAgente();
	
	public List<CalculoISN> getAllFechaCalculo();
	
	public List<CalculoISN> getAllColaborador();
	
	public CalculoIsnDTO consultarInfoCalculo(Integer id, String mes);
}
