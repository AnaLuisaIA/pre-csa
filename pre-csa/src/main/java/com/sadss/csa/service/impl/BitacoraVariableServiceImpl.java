package com.sadss.csa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.controller.beans.BitacoraVariableDTO;
import com.sadss.csa.dao.BitacoraVariableDAO;
import com.sadss.csa.modelo.entidad.BitacoraVariables;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.BitacoraVariablesService;
import com.sadss.csa.service.generic.AbstractService;

@Service
public class BitacoraVariableServiceImpl extends AbstractService<BitacoraVariables> implements BitacoraVariablesService{

	@Autowired
	private BitacoraVariableDAO dao;

	@Override
	public void validateBeforeCreate(BitacoraVariables entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(BitacoraVariables entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(BitacoraVariables entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BitacoraVariableDTO> getRegistros() {
		return  dao.getRegistros();
	}

	@Override
	protected IOperations<BitacoraVariables> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}
	
	
}
