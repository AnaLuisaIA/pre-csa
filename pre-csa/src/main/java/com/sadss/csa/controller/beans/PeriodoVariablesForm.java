package com.sadss.csa.controller.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.util.enums.TipoVariable;

public class PeriodoVariablesForm extends GenericForm<PeriodoVariablesForm, PeriodoVariable>{

	private Integer id;
	private Integer idVariable;
	private BigDecimal valor;
	private Date fechaAplicacion;
	private Date fechaTermino;
	private Variable variable;
	private String nombre;
	private String descripcion;
	private TipoVariable tipo;
	private String justificacion;
	
	public PeriodoVariablesForm() {
		
	}

	public PeriodoVariablesForm(Integer id, BigDecimal valor, Date fechaAplicacion, Date fechaTermino,
			Variable variable) {
		super();
		this.id = id;
		this.valor = valor;
		this.fechaAplicacion = fechaAplicacion;
		this.fechaTermino = fechaTermino;
		this.variable = variable;
	}
	

	public PeriodoVariablesForm(Integer id, Integer idVariable, BigDecimal valor, Date fechaAplicacion,
			Date fechaTermino, Variable variable, String nombre) {
		super();
		this.id = id;
		this.idVariable = idVariable;
		this.valor = valor;
		this.fechaAplicacion = fechaAplicacion;
		this.fechaTermino = fechaTermino;
		this.variable = variable;
		this.nombre = nombre;
	}

	public Integer getIdVariable() {
		return idVariable;
	}

	public void setIdVariable(Integer idVariable) {
		this.idVariable = idVariable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
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

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	
}
