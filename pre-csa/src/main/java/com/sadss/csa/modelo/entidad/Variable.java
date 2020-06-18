package com.sadss.csa.modelo.entidad;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.sadss.csa.modelo.generic.GenericModel;
import com.sadss.csa.util.enums.TipoVariable;

public class Variable extends GenericModel<Variable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	private String descripcion;
	private TipoVariable tipo;

	Set<PeriodoVariable> periodos = new HashSet<PeriodoVariable>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoVariable getTipo() {
		return tipo;
	}

	public void setTipo(TipoVariable tipo) {
		this.tipo = tipo;
	}

	public Set<PeriodoVariable> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(Set<PeriodoVariable> periodos) {
		this.periodos = periodos;
	}

}
