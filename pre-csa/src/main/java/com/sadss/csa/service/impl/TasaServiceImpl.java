package com.sadss.csa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.dao.TasaDAO;
import com.sadss.csa.modelo.entidad.BitacoraTasas;
import com.sadss.csa.modelo.entidad.TasaSobreNomina;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.BitacoraTasaService;
import com.sadss.csa.service.TasaService;
import com.sadss.csa.service.UsuarioService;
import com.sadss.csa.service.generic.AbstractService;

@Service
public class TasaServiceImpl extends AbstractService<TasaSobreNomina> implements TasaService{

	@Autowired
	private TasaDAO dao;
	
	@Autowired
	private BitacoraTasaService bitVariablesService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public void validateBeforeCreate(TasaSobreNomina entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(TasaSobreNomina entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(TasaSobreNomina entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TasaSobreNomina> findTasas() {
		return dao.findTasas();
	}

	@Override
	public TasaSobreNomina updateTasa(int id) {
		return dao.updateTasa(id);
	}

	@Override
	public void registrarAccionBitacora(String accion, Date fecha, String justificacion, String user) {
		BitacoraTasas bt = new BitacoraTasas();
		
		bt.setAccion(accion);
		bt.setFecha(fecha);
		bt.setJustificacion(justificacion);
		bt.setUsuario(usuarioService.findByUsername(user));
		
		bitVariablesService.create(bt);
	}

	@Override
	protected IOperations<TasaSobreNomina> getDao() {
		return dao;
	}

}
