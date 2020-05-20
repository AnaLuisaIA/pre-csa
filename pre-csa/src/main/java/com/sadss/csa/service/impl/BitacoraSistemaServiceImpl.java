package com.sadss.csa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.dao.BitacoraSistemaDAO;
import com.sadss.csa.modelo.entidad.Bitacora;
import com.sadss.csa.modelo.entidad.Usuario;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.BitacoraSistemaService;
import com.sadss.csa.service.generic.AbstractService;

@Service
public class BitacoraSistemaServiceImpl extends AbstractService<Bitacora> implements BitacoraSistemaService{

	@Autowired
	private BitacoraSistemaDAO dao;
	
	public BitacoraSistemaServiceImpl() {
		super();
	}
	
	@Override
	public void validateBeforeCreate(Bitacora entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(Bitacora entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(Bitacora entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bitacora> getAllBitacoras() {
		
		return dao.getAllBitacoras();
	}

	@Override
	public List<Bitacora> getBitacorasPorBusqueda(Bitacora b) {
		
		return dao.getBitacorasPorBusqueda(b);
	}

	@Override
	public void guardarRegistroAccion(String accion, Date fechas, Usuario user) {
		Bitacora b = new Bitacora();
		b.setAccion(accion);
		b.setFecha(fechas);
		b.setUsuario(user);
		
		dao.create(b);
		
	}

	@Override
	protected IOperations<Bitacora> getDao() {
		return dao;
	}

}
