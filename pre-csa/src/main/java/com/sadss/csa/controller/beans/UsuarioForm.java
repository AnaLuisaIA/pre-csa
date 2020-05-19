package com.sadss.csa.controller.beans;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.sadss.csa.controller.beans.UsuarioForm;
import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.Usuario;

public class UsuarioForm extends GenericForm<UsuarioForm, Usuario> {

	private Integer id;

	@NotNull
	private Boolean estado;

	@NotBlank
	private String numColaborador;

	private String nombres;
	private String aPaterno;
	private String aMaterno;

	private Set<String> permisos = new HashSet<String>(0);

	public UsuarioForm() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNumColaborador() {
		return numColaborador;
	}

	public void setNumColaborador(String numColaborador) {
		this.numColaborador = numColaborador;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getaPaterno() {
		return aPaterno;
	}

	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}

	public String getaMaterno() {
		return aMaterno;
	}

	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}

}
