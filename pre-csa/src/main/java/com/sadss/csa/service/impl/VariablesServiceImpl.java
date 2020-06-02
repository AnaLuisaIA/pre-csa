package com.sadss.csa.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import com.sadss.csa.controller.beans.VariablesForm;
import com.sadss.csa.controller.beans.VariablesDTO;
import com.sadss.csa.dao.VariableDAO;
import com.sadss.csa.modelo.entidad.BitacoraVariables;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.BitacoraVariablesService;
import com.sadss.csa.service.UsuarioService;
import com.sadss.csa.service.VariablesService;
import com.sadss.csa.service.generic.AbstractService;
import com.sadss.csa.service.generic.DuplicateValidator;

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
		validateDuplicates(entity, result);
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
	public List<PeriodoVariable> getPeriodos() throws ParseException{
		return this.dao.getPeriodos();
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
	
	/**
	 * Valida existencia de duplicados antes de guardar. Se basa en los campos:
	 * nombre
	 * 
	 * @param entity
	 * @param result
	 */
	private void validateDuplicates(Variable entity, BindingResult result) {
		ArrayList<String[]> props = new ArrayList<String[]>();
		props.add(new String[] { "nombre" });
		DuplicateValidator<Variable> validator = new DuplicateValidator<Variable>(Variable.class, this, props);
		ValidationUtils.invokeValidator(validator, entity, result);
	}

	@Override
	public List<VariablesDTO> getVariablesCalculo(Date fechaAplicacion, Date fechaTermino) {
		return this.dao.getVariablesCalculo(fechaAplicacion, fechaTermino);
	}

	@Override
	public PeriodoVariable findVariablesID(Integer id) {
		return dao.findVariablesID(id);
	}





	

}
