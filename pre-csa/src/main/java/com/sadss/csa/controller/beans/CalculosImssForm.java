package com.sadss.csa.controller.beans;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.CalculoIMSS;
import com.sadss.csa.util.enums.TipoNominaIMSS;
import com.sadss.csa.util.enums.TipoPeriodo;

public class CalculosImssForm extends GenericForm<CalculosImssForm, CalculoIMSS> {

	@NotNull
	private MultipartFile archivo;

	@NotNull
	private TipoPeriodo tipoPeriodo;

	@NotNull
	private TipoNominaIMSS tipoNomina;

	@NotNull
	private Date fechaInicio;

	@NotNull
	private Date fechaFin;

	private Integer fechaVariables;

	private Date fechaAplicacion;

	private Date fechaTermino;

	private String nombreArchivo;

	public CalculosImssForm() {
	}

	public TipoPeriodo getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(TipoPeriodo tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	public TipoNominaIMSS getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(TipoNominaIMSS tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	public MultipartFile getArchivo() {
		return archivo;
	}

	public void setArchivo(MultipartFile archivo) {
		this.archivo = archivo;
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

	public Integer getFechaVariables() {
		return fechaVariables;
	}

	public void setFechaVariables(Integer fechaVariables) {
		this.fechaVariables = fechaVariables;
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

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

}
