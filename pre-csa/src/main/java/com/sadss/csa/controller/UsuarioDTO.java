package com.sadss.csa.controller;

public class UsuarioDTO {

	private Integer id;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private boolean estado;

	public UsuarioDTO(Integer id, String nombre, String aPaterno, String aMaterno, boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
