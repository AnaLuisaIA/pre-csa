package com.sadss.csa.service;

import java.util.Date;
import java.util.List;

import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.TasaSobreNomina;

public interface TasaService extends CrudService<TasaSobreNomina>{

	/*
	 * Lista de Tasas
	 * */
	public List<TasaSobreNomina> findTasas();
	
	
	/*
	 * Metodo Actualizar Tasa
	 * */
	
	public TasaSobreNomina updateTasa(int id);
	
	/*
	 * Metodo Registro en Bitacora
	 * */
	public void registrarAccionBitacora(String accion,Date fecha, String  justificacion, String user);
}
