package com.sadss.csa.modelo.entidad;

import java.io.Serializable;

import com.sadss.csa.modelo.generic.GenericModel;

public class Usuario extends GenericModel<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String numColaborador;
	private Boolean estado;
	private String nombres;
	private String aPaterno;
	private String aMaterno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumColaborador() {
		return numColaborador;
	}

	public void setNumColaborador(String numColaborador) {
		this.numColaborador = numColaborador;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
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

}
