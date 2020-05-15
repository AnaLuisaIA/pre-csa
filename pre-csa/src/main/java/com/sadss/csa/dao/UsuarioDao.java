package com.sadss.csa.dao;

import com.sadss.csa.modelo.entidad.Usuario;
import com.sadss.csa.modelo.generic.IOperations;

public interface UsuarioDao extends IOperations<Usuario> {

	/**
	 * Obtiene el objeto del usuario mediante su username
	 * @param username
	 * @return Usuario
	 */
	public Usuario findByUsername(String username);
}
