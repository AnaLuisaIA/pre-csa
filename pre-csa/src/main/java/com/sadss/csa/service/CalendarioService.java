package com.sadss.csa.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.Calendario;

public interface CalendarioService extends CrudService<Calendario> {
	
	/**
	 * Método para descargar el layout del calendario ISN en Excel
	 * @param request
	 * @param response
	 */
	public void descargarCalendarioXLSX(HttpServletRequest request,
			HttpServletResponse response);
	
	/**
	 * Obtene los años que existen en el calendario
	 * @return Lista de años
	 */
	public List<Integer> obtenerAnios();
	
	/**
	 * Obtiene todos los registros de un calendario dado un año
	 * @param anio Año de búsqueda
	 * @return Lista de fechas del calendario
	 */
	public List<Calendario> getCalendarioPorAnio(Integer anio);
	
	/**
	 * Se registra en la bitácora de Calendario al acción realizada
	 * @param accion Descripción de la acción
	 * @param fecha Fecha y hora en la que se realizó la acción
	 * @param user Colaborador que realizó la acción
	 */
	public void registrarAccionBitacora(String accion, Date fecha, String user);

}
