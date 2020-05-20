package com.sadss.csa.modelo.entidad;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.sadss.csa.modelo.generic.GenericModel;

public class Bitacora extends GenericModel<Bitacora> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String accion;
	private Date fecha;
	private Usuario usuario;
	
	private Date fechaInicio;
	private Date fechaFin;

	public Bitacora() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		
		fechaInicio = calendar.getTime();
		fechaFin = new Date();
	}
	
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	
}
