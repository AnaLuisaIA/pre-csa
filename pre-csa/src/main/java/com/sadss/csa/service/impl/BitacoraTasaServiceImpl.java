package com.sadss.csa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.controller.beans.BitacoraTasaDTO;
import com.sadss.csa.dao.BitacoraTasaDAO;
import com.sadss.csa.modelo.entidad.BitacoraTasas;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.BitacoraTasaService;
import com.sadss.csa.service.generic.AbstractService;

@Service
public class BitacoraTasaServiceImpl extends AbstractService<BitacoraTasas> implements BitacoraTasaService{

	@Autowired
	private BitacoraTasaDAO dao;
	
	@Override
	public void validateBeforeCreate(BitacoraTasas entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(BitacoraTasas entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(BitacoraTasas entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BitacoraTasaDTO> getRegistros() {
		return dao.getRegistros();
	}

	@Override
	protected IOperations<BitacoraTasas> getDao() {
		return this.dao;
	}

}
