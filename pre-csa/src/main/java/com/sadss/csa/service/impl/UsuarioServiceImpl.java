package com.sadss.csa.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import com.sadss.csa.modelo.entidad.Usuario;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.UsuarioService;
import com.sadss.csa.service.generic.AbstractService;
import com.segurosargos.crece.api.ColaboradorDAO;
import com.segurosargos.crece.modelo.entidad.Colaborador;
import com.sadss.csa.dao.UsuarioDao;
import com.sadss.csa.service.generic.DuplicateValidator;

@Service
public class UsuarioServiceImpl extends AbstractService<Usuario> implements UsuarioService {

	@Autowired
	private UsuarioDao dao;

	@Resource
	private ColaboradorDAO colaboradorDAO;

	@Override
	public void validateBeforeCreate(Usuario entity, BindingResult result) {
		validateDuplicates(entity, result);
	}

	@Override
	public void validateBeforeUpdate(Usuario entity, BindingResult result) {
	}

	@Override
	public void validateBeforeDelete(Usuario entity, BindingResult result) {
	}

	@Override
	public LinkedHashMap<String, String> getListColaboradores() {
		
		LinkedHashMap<String, String> lista = new LinkedHashMap<String, String>();
		List<Colaborador> colabs = this.colaboradorDAO.consultaColaboradores();
		if (colabs != null && !colabs.isEmpty()) {
			for (Colaborador colaborador : colabs) {
				if (colaborador != null) {
					StringBuilder str = new StringBuilder(colaborador.getNumeroColaborador());
					str.append(" - ").append(colaborador.getNombre()).append(" ");
					str.append(colaborador.getApPaterno()).append(" ").append(colaborador.getApMaterno());
					lista.put(colaborador.getNumeroColaborador(), str.toString());
				}
			}
		}

		return lista;
	}

	@Override
	public List<String> getPermisosByUsername(String username) {
		List<String> permisos = new ArrayList<String>();
		Usuario perfil = this.dao.findByUsername(username);
		
		if(perfil!=null){			
			for (String permisoType : perfil.getPermisos()) {
				permisos.add(permisoType);
			}			
		}
		return permisos;
	}
	
	@Override
	public Usuario findByUsername(String username) {
		return this.dao.findByUsername(username);
	}

	@Override
	protected IOperations<Usuario> getDao() {
		return this.dao;
	}

	/**
	 * Valida existencia de duplicados antes de guardar. Se basa en los campos:
	 * username
	 * 
	 * @param entity
	 * @param result
	 */
	private void validateDuplicates(Usuario entity, BindingResult result) {
		ArrayList<String[]> props = new ArrayList<String[]>();
		props.add(new String[] { "numColaborador" });
		DuplicateValidator<Usuario> validator = new DuplicateValidator<Usuario>(Usuario.class, this, props);
		ValidationUtils.invokeValidator(validator, entity, result);
	}

}
