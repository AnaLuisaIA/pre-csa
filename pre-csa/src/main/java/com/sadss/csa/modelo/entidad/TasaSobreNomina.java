package com.sadss.csa.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sadss.csa.modelo.generic.GenericModel;
import com.sadss.csa.util.enums.TipoNomina;
import com.sadss.csa.util.enums.TipoVariableTasa;

public class TasaSobreNomina extends GenericModel<TasaSobreNomina> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Boolean estado;
	private BigDecimal valor;
	private Date fechaAplicacion;
	private TipoVariableTasa tipoVariable;
	private TipoNomina tipoNomina;
	private String oficina;

	private Set<BitacoraTasas> registros = new HashSet<BitacoraTasas>();

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

	public TipoVariableTasa getTipoVariable() {
		return tipoVariable;
	}

	public void setTipoVariable(TipoVariableTasa tipoVariable) {
		this.tipoVariable = tipoVariable;
	}

	public TipoNomina getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(TipoNomina tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public Set<BitacoraTasas> getRegistros() {
		return registros;
	}

	public void setRegistros(Set<BitacoraTasas> registros) {
		this.registros = registros;
	}

}
