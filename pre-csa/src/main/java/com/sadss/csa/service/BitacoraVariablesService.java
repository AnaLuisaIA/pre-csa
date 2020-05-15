package com.sadss.csa.service;

import java.util.Date;

import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.BitacoraVariables;
import com.sadss.csa.modelo.entidad.Usuario;


public interface BitacoraVariablesService extends CrudService<BitacoraVariables>{

	public void guardarRegistroAccion(String accion, Date fecha, Usuario user);
}
