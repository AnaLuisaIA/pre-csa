package com.sadss.csa.controller.beans;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.util.enums.TipoVariable;

public class VariablesForm extends GenericForm<VariablesForm, Variable> {

	private Integer id;

	@NotNull
	@NotEmpty
	@Size(max = 100)
	private String nombre;
	private String descripcion;
	private TipoVariable tipo;
	private Boolean estado;
	private Integer idPeriodo;
	private BigDecimal valor;
	private BigDecimal valorn;
	private Date fechaAplicacion;
	private Date fechaAplicacionn;
	private Date fechaTermino;

	private String justificacion;

	public VariablesForm() {

	}

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

	
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public TipoVariable getTipo() {
		return tipo;
	}

	public void setTipo(TipoVariable tipo) {
		this.tipo = tipo;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
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

	public BigDecimal getValorn() {
		return valorn;
	}

	public void setValorn(BigDecimal valorn) {
		this.valorn = valorn;
	}

	public Date getFechaAplicacionn() {
		return fechaAplicacionn;
	}

	public void setFechaAplicacionn(Date fechaAplicacionn) {
		this.fechaAplicacionn = fechaAplicacionn;
	}

	
}
