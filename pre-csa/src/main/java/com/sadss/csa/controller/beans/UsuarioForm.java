package com.sadss.csa.controller.beans;

import java.util.HashSet;
import java.util.Set;

import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.Usuario;

public class UsuarioForm extends GenericForm<UsuarioForm, Usuario> {

	private Integer id;
	private String numColaborador;
	private Boolean estado;
	private Set<String> permisos = new HashSet<String>(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumColaborador() {
		return numColaborador;
	}

	public void setNumColaborador(String numColaborador) {
		this.numColaborador = numColaborador;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Set<String> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<String> permisos) {
		this.permisos = permisos;
	}

}
