package com.sadss.csa.controller.beans;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.TasaSobreNomina;
import com.sadss.csa.util.enums.TipoNomina;
import com.sadss.csa.util.enums.TipoVariableTasa;

public class TasaForm  extends GenericForm<TasaForm, TasaSobreNomina>{
	
	private Integer id;
	
	@NotNull
	@NotEmpty
	@Size(max = 100)
	private String estado;
	private TipoNomina tipoNomina;

	private TipoVariableTasa tipoVariable;

	private BigDecimal valor;
	private String oficina;
	private Date fechaAplicacion;
	private Boolean estatus;
	
	private String justificacion;
	
	SimpleDateFormat sd = new SimpleDateFormat();
	
	public TasaForm() {
		
	}

	public Integer getId() {
		return id;
	}
	

	public TipoVariableTasa getTipoVariable() {
		return tipoVariable;
	}

	public void setTipoVariable(TipoVariableTasa tipoVariable) {
		this.tipoVariable = tipoVariable;
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

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public SimpleDateFormat getSd() {
		return sd;
	}

	public void setSd(SimpleDateFormat sd) {
		this.sd = sd;
	}
	
	
}
