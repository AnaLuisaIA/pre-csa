package com.sadss.csa.modelo.entidad;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.sadss.csa.modelo.generic.GenericModel;

public class Usuario extends GenericModel<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String numColaborador;
	private Boolean estado;
	private String nombres;
	private String aPaterno;
	private String aMaterno;

	private Set<String> permisos = new HashSet<String>(0);

	private Set<Bitacora> accionesSistema = new HashSet<Bitacora>();
	private Set<BitacoraCalendario> accionesCalendario = new HashSet<BitacoraCalendario>();
	private Set<BitacoraTasas> accionesTasas = new HashSet<BitacoraTasas>();
	private Set<BitacoraVariables> accionesVariables = new HashSet<BitacoraVariables>();

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

	public Set<Bitacora> getAccionesSistema() {
		return accionesSistema;
	}

	public void setAccionesSistema(Set<Bitacora> accionesSistema) {
		this.accionesSistema = accionesSistema;
	}

	public Set<BitacoraCalendario> getAccionesCalendario() {
		return accionesCalendario;
	}

	public void setAccionesCalendario(Set<BitacoraCalendario> accionesCalendario) {
		this.accionesCalendario = accionesCalendario;
	}

	public Set<BitacoraTasas> getAccionesTasas() {
		return accionesTasas;
	}

	public void setAccionesTasas(Set<BitacoraTasas> accionesTasas) {
		this.accionesTasas = accionesTasas;
	}

	public Set<BitacoraVariables> getAccionesVariables() {
		return accionesVariables;
	}

	public void setAccionesVariables(Set<BitacoraVariables> accionesVariables) {
		this.accionesVariables = accionesVariables;
	}

	public Set<String> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<String> permisos) {
		this.permisos = permisos;
	}

}
