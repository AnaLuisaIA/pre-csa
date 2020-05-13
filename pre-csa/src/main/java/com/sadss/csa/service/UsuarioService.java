package com.sadss.csa.service;

import java.util.LinkedHashMap;
import java.util.List;

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
}
