package com.sadss.csa.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.Usuario;

public interface UsuarioService extends CrudService<Usuario>{

	/**
	 * Obtiene la lista de colaboradores que se pueden configurar como usuarios del sistema
	 * @return Lista [Número colaborador, Nombre] para ser usada en la GUI de perfil de usuario
	 */
	public LinkedHashMap<String, String> getListColaboradores();
	
	/**
	 * Obtiene la lista de permisos asignados al usuario
	 * @param username nombre de usuario a buscar
	 * @return lista de permisos asignados al usuario
	 */
	public List<String> getPermisosByUsername(String username);
	
	/**
	 * Busca el perfil de usuario para el nombre de usuario propocionado
	 * @param username nombre de usuario a buscar
	 * @return perfil del usuario
	 */
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username);
	
	/**
	 * Se registra en la bit�cora de Calendario al acci�n realizada
	 * @param accion Descripci�n de la acci�n
	 * @param fecha Fecha y hora en la que se realiz� la acci�n
	 * @param user Colaborador que realiz� la acci�n
	 */
	public void registrarAccionBitacora(String accion, Date fecha, String user);
}
