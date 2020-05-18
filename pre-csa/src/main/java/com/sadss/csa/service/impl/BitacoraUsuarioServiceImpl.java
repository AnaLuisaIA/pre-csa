package com.sadss.csa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.controller.beans.BitacoraUsuarioDTO;
import com.sadss.csa.dao.BitacoraUsuarioDao;
import com.sadss.csa.modelo.entidad.BitacoraUsuario;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.BitacoraUsuarioService;
import com.sadss.csa.service.generic.AbstractService;

@Service
public class BitacoraUsuarioServiceImpl extends AbstractService<BitacoraUsuario> implements BitacoraUsuarioService{

	@Autowired
	private BitacoraUsuarioDao dao;

	@Override
	protected IOperations<BitacoraUsuario> getDao() {
		return this.dao;
	}

	@Override
	public List<BitacoraUsuarioDTO> getRegistros() {
		return dao.getRegistros();
	}

	@Override
	public void validateBeforeCreate(BitacoraUsuario entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(BitacoraUsuario entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(BitacoraUsuario entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

}
