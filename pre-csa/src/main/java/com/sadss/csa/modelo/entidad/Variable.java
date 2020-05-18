package com.sadss.csa.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sadss.csa.modelo.generic.GenericModel;
import com.sadss.csa.util.enums.TipoVariable;

public class Variable extends GenericModel<Variable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	private String descripcion;
	private BigDecimal valor;
	
	private Date fechaAplicacion;
	
	private TipoVariable tipo;
	private Boolean estado;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public TipoVariable getTipo() {
		return tipo;
	}

	public void setTipo(TipoVariable tipo) {
		this.tipo = tipo;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
