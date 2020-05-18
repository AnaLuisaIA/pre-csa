package com.sadss.csa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.dao.VariableDAO;
import com.sadss.csa.modelo.entidad.BitacoraVariables;
import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.BitacoraVariablesService;
import com.sadss.csa.service.UsuarioService;
import com.sadss.csa.service.VariablesService;
import com.sadss.csa.service.generic.AbstractService;

@Service
public class VariablesServiceImpl  extends AbstractService<Variable> implements VariablesService{

	@Autowired 
	private VariableDAO dao;
	
	@Autowired
	private BitacoraVariablesService bitVariablesService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public void validateBeforeCreate(Variable entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(Variable entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(Variable entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public List<Variable> findVariables() {
		return dao.findVariables();
	}

	@Override
	protected IOperations<Variable> getDao() {
		return dao;
	}

	@Override
	public Variable updateVariable(int id) {
		return dao.updateVariable(id);
		
	}

	@Override
	public void registrarAccionBitacora(String accion, Date fecha, String justificacion, String user) {

		BitacoraVariables bv = new BitacoraVariables();

		bv.setAccion(accion);
		bv.setFechaAccion(fecha);
		bv.setJustificacion(justificacion);
		bv.setUsuario(usuarioService.findByUsername(user));
		
		bitVariablesService.create(bv);

		
	}


	

}
