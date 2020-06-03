package com.sadss.csa.controller.beans;

import java.math.BigDecimal;

import com.sadss.csa.util.enums.TipoVariable;

public class VariablesDTO {

	private Integer id;
	private String nombre;
	private String descripcion;
	private TipoVariable tipo;
	private Boolean estado;
	private BigDecimal valor;

	public VariablesDTO(Integer id, String nombre, String descripcion, TipoVariable tipo, Boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.estado = estado;
	}

	public VariablesDTO(Integer id, String nombre, BigDecimal valor, TipoVariable tipo) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.valor = valor;
	}

	public VariablesDTO(Integer id, Boolean estado) {
		super();
		this.id = id;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoVariable getTipo() {
		return tipo;
	}

	public void setTipo(TipoVariable tipo) {
		this.tipo = tipo;
	}

}
