package com.sadss.csa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.dao.calculoIsnDAO;
import com.sadss.csa.modelo.entidad.CalculoISN;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.CalculoIsnService;
import com.sadss.csa.service.generic.AbstractService;

@Service
public class CalculoIsnServiceImpl extends AbstractService<CalculoISN> implements CalculoIsnService{

	@Autowired
	private calculoIsnDAO dao;
	
	@Override
	public void validateBeforeCreate(CalculoISN entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(CalculoISN entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(CalculoISN entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CalculoISN> getAllCalculoISN() {
		return dao.getAllCalculoISN();
	}

	@Override
	public List<CalculoISN> getCalculosISNPorBusqueda(CalculoISN c) {
		return dao.getAllCalculoISNPorBusqueda(c);
	}

	@Override
	protected IOperations<CalculoISN> getDao() {
		return this.dao;
	}

	@Override
	public List<CalculoISN> getAllAgente() {
		return dao.getAllAgente();
	}

	@Override
	public List<CalculoISN> getAllFechaCalculo() {
		return dao.getAllFechaCalculo();
	}

	@Override
	public List<CalculoISN> getAllColaborador() {
		return dao.getAllColaborador();
	}

}
