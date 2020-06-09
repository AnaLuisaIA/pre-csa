package com.sadss.csa.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sadss.csa.modelo.generic.GenericModel;
import com.sadss.csa.util.enums.TipoNomina;
import com.sadss.csa.util.enums.TipoVariableTasa;

public class TasaSobreNomina extends GenericModel<TasaSobreNomina> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String estado;
	private TipoNomina tipoNomina;
	private TipoVariableTasa tipoVariable;
	private BigDecimal valor;
	private BigDecimal totalAPagar;
	private String oficina;
	private Date fechaAplicacion;
	private Boolean estatus;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(BigDecimal totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

}
