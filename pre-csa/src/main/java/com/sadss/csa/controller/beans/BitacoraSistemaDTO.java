package com.sadss.csa.controller.beans;

import java.util.Date;

public class BitacoraSistemaDTO {

	private String nombreUsuario;
	private String accion;
	private Date fechaAccion;
	
	public BitacoraSistemaDTO(String nombreUsuario, String accion, Date fechaAccion) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.accion = accion;
		this.fechaAccion = fechaAccion;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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
	
	
}
