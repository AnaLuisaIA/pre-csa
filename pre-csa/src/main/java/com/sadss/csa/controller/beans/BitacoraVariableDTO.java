package com.sadss.csa.controller.beans;

import java.util.Date;

public class BitacoraVariableDTO {

	private String accion;
	private Date fechaAccion;
	private String numColaborador;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private String justificacion;

	public BitacoraVariableDTO(String accion, Date fechaAccion, String numColaborador, String nombre, String aPaterno,
			String aMaterno, String justificacion) {
		super();
		this.accion = accion;
		this.fechaAccion = fechaAccion;
		this.numColaborador = numColaborador;
		this.nombre = nombre;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.justificacion = justificacion;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Date getFechaAccion() {
		return fechaAccion;
	}

	public void setFechaAccion(Date fechaAccion) {
		this.fechaAccion = fechaAccion;
	}

	public String getNumColaborador() {
		return numColaborador;
	}

	public void setNumColaborador(String numColaborador) {
		this.numColaborador = numColaborador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getaPaterno() {
		return aPaterno;
	}

	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}

	public String getaMaterno() {
		return aMaterno;
	}

	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

}
