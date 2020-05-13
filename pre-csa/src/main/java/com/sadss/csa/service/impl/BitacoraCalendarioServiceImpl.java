package com.sadss.csa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.controller.beans.BitacoraCalendarioDTO;
import com.sadss.csa.dao.BitacoraCalendarioDao;
import com.sadss.csa.modelo.entidad.BitacoraCalendario;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.BitacoraCalendarioService;
import com.sadss.csa.service.generic.AbstractService;

@Service
public class BitacoraCalendarioServiceImpl extends AbstractService<BitacoraCalendario> implements BitacoraCalendarioService{

	@Autowired
	private BitacoraCalendarioDao dao;
	
	@Override
	public void validateBeforeCreate(BitacoraCalendario entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(BitacoraCalendario entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(BitacoraCalendario entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IOperations<BitacoraCalendario> getDao() {
		return this.dao;
	}

	@Override
	public List<BitacoraCalendarioDTO> getRegistros() {
		return dao.getRegistros();
	}

}
