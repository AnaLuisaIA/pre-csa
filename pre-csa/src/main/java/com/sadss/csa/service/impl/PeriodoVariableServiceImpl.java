package com.sadss.csa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.dao.PeriodoVariableDAO;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.PeriodoVariableService;
import com.sadss.csa.service.generic.AbstractService;

@Service
public class PeriodoVariableServiceImpl extends AbstractService<PeriodoVariable> implements PeriodoVariableService{

	@Autowired
	private PeriodoVariableDAO dao;
	
	@Override
	public void validateBeforeCreate(PeriodoVariable entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(PeriodoVariable entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(PeriodoVariable entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IOperations<PeriodoVariable> getDao() {
		return dao;
	}

}
