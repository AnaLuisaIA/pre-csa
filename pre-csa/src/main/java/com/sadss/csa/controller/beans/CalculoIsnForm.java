package com.sadss.csa.controller.beans;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.CalculoISN;
import com.sadss.csa.util.enums.TipoPeriodo;

public class CalculoIsnForm extends GenericForm<CalculoIsnForm, CalculoISN> {

	private String nombreArchivo;

	@NotNull
	private TipoPeriodo tipoPeriodo;

	@NotNull
	private Date fechaInicio;

	@NotNull
	private Date fechaFin;

	@NotNull
	private Integer anioCalendario;

	@NotNull
	private Integer semanaCalendario;

	public CalculoIsnForm() {
	}

	public TipoPeriodo getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(TipoPeriodo tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getAnioCalendario() {
		return anioCalendario;
	}

	public void setAnioCalendario(Integer anioCalendario) {
		this.anioCalendario = anioCalendario;
	}

	public Integer getSemanaCalendario() {
		return semanaCalendario;
	}

	public void setSemanaCalendario(Integer semanaCalendario) {
		this.semanaCalendario = semanaCalendario;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

}
