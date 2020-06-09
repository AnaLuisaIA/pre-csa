package com.sadss.csa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.dao.DatosCargaDao;
import com.sadss.csa.modelo.entidad.DatosCarga;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.DatosCargaService;
import com.sadss.csa.service.generic.AbstractService;
import com.sadss.csa.util.enums.TipoPeriodo;

@Service
public class DatosCargaServiceImpl extends AbstractService<DatosCarga> implements DatosCargaService {

	@Autowired
	private DatosCargaDao dao;
	
	@Override
	protected IOperations<DatosCarga> getDao() {
		return this.dao;
	}

	@Override
	public void validateBeforeCreate(DatosCarga entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(DatosCarga entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(DatosCarga entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DatosCarga> findDatosByPeriodo(Date fechaInicio, Date fechaFin, TipoPeriodo periodo) {
		return this.dao.findDatosByPeriodo(fechaInicio, fechaFin, periodo);
	}

}
