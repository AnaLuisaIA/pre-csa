package com.sadss.csa.modelo.entidad;

import java.io.Serializable;
import java.util.Date;

import com.sadss.csa.modelo.generic.GenericModel;

public class BitacoraUsuario extends GenericModel<BitacoraUsuario> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String accion;
	private Usuario usuario;
	private Date fechaAccion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaAccion() {
		return fechaAccion;
	}

	public void setFechaAccion(Date fechaAccion) {
		this.fechaAccion = fechaAccion;
	}

}
