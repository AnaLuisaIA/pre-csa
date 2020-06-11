package com.sadss.csa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import com.sadss.csa.dao.TasaDAO;
import com.sadss.csa.modelo.entidad.Bitacora;
import com.sadss.csa.modelo.entidad.BitacoraTasas;
import com.sadss.csa.modelo.entidad.TasaSobreNomina;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.BitacoraSistemaService;
import com.sadss.csa.service.BitacoraTasaService;
import com.sadss.csa.service.TasaService;
import com.sadss.csa.service.UsuarioService;
import com.sadss.csa.service.generic.AbstractService;
import com.sadss.csa.service.generic.DuplicateValidator;
import com.segurosargos.crece.api.CiudadesDAO;
import com.segurosargos.crece.modelo.entidad.Ciudad;
import com.segurosargos.crece.modelo.entidad.Colaborador;

@Service
public class TasaServiceImpl extends AbstractService<TasaSobreNomina> implements TasaService{

	@Autowired
	private TasaDAO dao;
	
	@Resource
	private CiudadesDAO ciudadesDAO;
	
	@Autowired
	private BitacoraTasaService bitVariablesService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BitacoraSistemaService bsService;
	
	@Override
	public void validateBeforeCreate(TasaSobreNomina entity, BindingResult result) {
		validateDuplicate(entity, result);
		
	}

	@Override
	public void validateBeforeUpdate(TasaSobreNomina entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(TasaSobreNomina entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Valida la existencia de duplicados antes de registrar en la base de datos, basandose en el campo estado
	 * 
	 * @param entity
	 * @param result
	 * */
	private void validateDuplicate(TasaSobreNomina entity , BindingResult result) {
		ArrayList<String[]> props = new ArrayList<String[]>();
		props.add(new String[] {"estado"});
		DuplicateValidator<TasaSobreNomina> validator =new DuplicateValidator<TasaSobreNomina>(TasaSobreNomina.class, this, props);
		ValidationUtils.invokeValidator(validator, entity, result);
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

	@Override
	public void registrarAccionBitacoraG(String accion, Date fecha, String user) {
		Bitacora b = new Bitacora();
		b.setAccion(accion);
		b.setFecha(fecha);
		b.setUsuario(usuarioService.findByUsername(user));
		
		bsService.create(b);
	}

	@Override
	public TasaSobreNomina getTasaByOficina(String oficina) {
		return this.dao.getTasaByOficina(oficina);
	}

	@Override
	public TasaSobreNomina findTasaByEstado(String estado) {
		return this.dao.findTasaByEstado(estado);
	}

	@Override
	public LinkedHashMap<String, String> getListCiudades() {
		LinkedHashMap<String, String> lista = new LinkedHashMap<String, String>();
		List<Ciudad> ciud = this.ciudadesDAO.consultaCiudades();
		if(ciud != null) {
			for(Ciudad ciudad : ciud) {
				if(ciudad != null) {
					StringBuilder str = new StringBuilder(ciudad.getLabel());
					lista.put(ciudad.getLabel(), str.toString());
				}
			}
		}
		return lista;
	}


}
