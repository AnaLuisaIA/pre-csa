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
	 * Metodo Registro en Bitacora Tasas
	 * */
	public void registrarAccionBitacora(String accion,Date fecha, String  justificacion, String user);
	
	/*
	 * Metodo Registro en Bitacora General
	 * */
	public void registrarAccionBitacoraG(String accion,Date fecha, String user);
	
	/**
	 * Obtener Tasa sobre Nómina dada una oficina
	 * @param oficina
	 * @return Objeto Tasa
	 */
	public TasaSobreNomina getTasaByOficina(String oficina);
	
	/**
	 * Obtiene una Tasa dado un estado
	 * @param estado
	 * @return Objeto Tasa
	 */
	public TasaSobreNomina findTasaByEstado(String estado);
}
