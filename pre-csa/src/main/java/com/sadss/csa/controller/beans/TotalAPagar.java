package com.sadss.csa.controller.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class TotalAPagar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String estado;
	private BigDecimal valor;

	public TotalAPagar() {
	}

	public TotalAPagar(String estado, BigDecimal valor) {
		this.estado = estado;
		this.valor = valor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
