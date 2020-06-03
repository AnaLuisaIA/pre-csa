package com.sadss.csa.controller.beans;

import java.util.Date;

public class BitacoraSistemaDTO {

	private String accion;
	private String numColaborador;
	private String nombres;
	private String aPaterno;
	private String aMaterno;
	private Date fecha;
	

	public BitacoraSistemaDTO(String accion, String numColaborador, String nombres, String aPaterno, String aMaterno,
			Date fecha) {
		super();
		this.accion = accion;
		this.numColaborador = numColaborador;
		this.nombres = nombres;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.fecha = fecha;
	}



	public String getNumColaborador() {
		return numColaborador;
	}



	public void setNumColaborador(String numColaborador) {
		this.numColaborador = numColaborador;
	}



	public String getNombres() {
		return nombres;
	}



	public void setNombres(String nombres) {
		this.nombres = nombres;
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



	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
