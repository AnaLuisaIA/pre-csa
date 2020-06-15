package com.sadss.csa.dao;

import java.util.Date;
import java.util.List;

import com.sadss.csa.modelo.entidad.Calendario;
import com.sadss.csa.modelo.generic.IOperations;

public interface CalendarioDao extends IOperations<Calendario> {

	/**
	 * Se agrupan los años existentes de calendarios
	 * @return Lista de años
	 */
	public List<Integer> obtenerAnios();
	
	/**
	 * Realiza la consulta para obtener las fechas que integran el calendario dado
	 * un año en específico
	 * @param anio Año de búsqueda
	 * @return Lista de fechas de calendario
	 */
	public List<Calendario> getCalendarioPorAnio(Integer anio);
	
	/**
	 * Obtiene el número de semanas de un mes
	 * @param mes Mes del calendario
	 * @return Número se semanas
	 */
	public Integer getNumeroSemanasByMes(String mes);
	
	/**
	 * Obtiene lista de fechas iniciales de todos los calendarios
	 * @return Lista de fechas
	 */
	public List<Date> getSemanasIniciales();
	
	/**
	 * Obtiene lista de fechas finales de todos los calendarios
	 * @return Lista de fechas
	 */
	public List<Date> getSemanasFinales();
}
