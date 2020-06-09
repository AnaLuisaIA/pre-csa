package com.sadss.csa.dao;

import java.util.List;

import com.sadss.csa.modelo.entidad.TasaSobreNomina;
import com.sadss.csa.modelo.generic.IOperations;

public interface TasaDAO extends IOperations<TasaSobreNomina>{

	public List<TasaSobreNomina> findTasas();
	
	public TasaSobreNomina updateTasa(int id);
	
	public TasaSobreNomina getTasaByOficina(String oficina);
	
	public TasaSobreNomina findTasaByEstado(String estado);
}
