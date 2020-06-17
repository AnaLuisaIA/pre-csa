package com.sadss.csa.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.Calendario;

public interface CalendarioService extends CrudService<Calendario> {
	
	/**
	 * M�todo para descargar el layout del calendario ISN en Excel
	 * @param request
	 * @param response
	 */
	public void descargarCalendarioXLSX(HttpServletRequest request,
			HttpServletResponse response);
	
	/**
	 * Obtene los a�os que existen en el calendario
	 * @return Lista de a�os
	 */
	public List<Integer> obtenerAnios();
	
	/**
	 * Obtiene todos los registros de un calendario dado un a�o
	 * @param anio A�o de b�squeda
	 * @return Lista de fechas del calendario
	 */
	public List<Calendario> getCalendarioPorAnio(Integer anio);
	
	/**
	 * Obtiene el n�mero de semanas dado un mes
	 * @param mes Mes (en palabra) 
	 * @return N�mero de semanas
	 */
	public Integer getNumeroSemanasByMes(String mes);
	
	/**
	 * Se registra en la bit�cora de Calendario al acci�n realizada
	 * @param accion Descripci�n de la acci�n
	 * @param fecha Fecha y hora en la que se realiz� la acci�n
	 * @param user Colaborador que realiz� la acci�n
	 */
	public void registrarAccionBitacora(String accion, Date fecha, String user);
	
	/**
	 * Obtiene las semanas iniciales de todos los calendarios
	 * @return Lista de fechas
	 */
	public List<Date> getSemanasIniciales();
	
	/**
	 * Obtiene las semanas finales de todos los calendarios
	 * @return Lista de fechas
	 */
	public List<Date> getSemanasFinales();
	
	/**
	 * Carga archivo Calendario a directorio FTP
	 * @param nombre Nombre del archivo
	 * @param bytes Array de bytes de archivo
	 * @return true - El archivo se carg� con �xtio
	 * 		   false - El archivo no se carg� correctamente
	 */
	public boolean cargarArchivo(String nombre, byte[] bytes);
	
	/**
	 * Elimina todos los registros de un Calendario
	 * @param anio A�o del calendario
	 */
	public void deleteCalendarioPorAnio(Integer anio);

}
