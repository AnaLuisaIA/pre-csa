package com.sadss.csa.controller.beans;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sadss.csa.util.enums.TipoNomina;
import com.sadss.csa.util.enums.TipoVariable;
import com.sadss.csa.util.enums.TipoVariableTasa;

public class TasaDTO {

	private Integer id;
	private String estado;
	private TipoNomina tipoNomina;
	private TipoVariableTasa tipoVariable;
	private BigDecimal valor;
	private String oficina;
	private Date fechaAplicacion;
	private Boolean estatus;
	
	
	public TasaDTO(Integer id, String estado, TipoNomina tipoNomina, TipoVariableTasa tipoVariable, BigDecimal valor,
			String oficina, Date fechaAplicacion, Boolean estatus) {
		super();
		this.id = id;
		this.estado = estado;
		this.tipoNomina = tipoNomina;
		this.tipoVariable = tipoVariable;
		this.valor = valor;
		this.oficina = oficina;
		this.fechaAplicacion = fechaAplicacion;
		this.estatus = estatus;
	}
	public TasaDTO(Integer id, Boolean estatus) {
		super();
		this.id = id;
		this.estatus = estatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public TipoNomina getTipoNomina() {
		return tipoNomina;
	}
	public void setTipoNomina(TipoNomina tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	
	public TipoVariableTasa getTipoVariable() {
		return tipoVariable;
	}
	public void setTipoVariable(TipoVariableTasa tipoVariable) {
		this.tipoVariable = tipoVariable;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public Date getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public Boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	
	
}
