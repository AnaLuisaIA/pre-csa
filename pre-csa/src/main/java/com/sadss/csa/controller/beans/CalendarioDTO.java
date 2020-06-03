package com.sadss.csa.controller.beans;

import java.util.Date;

public class CalendarioDTO {

	private Integer id;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer trimestre;
	private Integer anio;
	private Integer semana;
	private String mes;

	public CalendarioDTO(Integer id, Date fechaInicio, Date fechaFin, Integer trimestre, Integer anio, Integer semana,
			String mes) {
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.trimestre = trimestre;
		this.anio = anio;
		this.semana = semana;
		this.mes = mes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getSemana() {
		return semana;
	}

	public void setSemana(Integer semana) {
		this.semana = semana;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

}
