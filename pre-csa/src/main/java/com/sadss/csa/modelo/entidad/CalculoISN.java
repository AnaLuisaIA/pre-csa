package com.sadss.csa.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sadss.csa.modelo.generic.GenericModel;

public class CalculoISN extends GenericModel<CalculoISN> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer claveAgente;
	private Integer localidad;
	private BigDecimal tasa;
	private Integer baseGravable;
	private Integer isnMensual;
	private Integer isnSemanal;
	private Usuario usuarioCalculo;
	private Date fechaCalculo;
	private Calendario calendario;

	private Integer idSemana;
	private String numColaborador;

	public CalculoISN() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClaveAgente() {
		return claveAgente;
	}

	public void setClaveAgente(Integer claveAgente) {
		this.claveAgente = claveAgente;
	}

	public Integer getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Integer localidad) {
		this.localidad = localidad;
	}

	public BigDecimal getTasa() {
		return tasa;
	}

	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}

	public Integer getBaseGravable() {
		return baseGravable;
	}

	public void setBaseGravable(Integer baseGravable) {
		this.baseGravable = baseGravable;
	}

	public Integer getIsnMensual() {
		return isnMensual;
	}

	public void setIsnMensual(Integer isnMensual) {
		this.isnMensual = isnMensual;
	}

	public Integer getIsnSemanal() {
		return isnSemanal;
	}

	public void setIsnSemanal(Integer isnSemanal) {
		this.isnSemanal = isnSemanal;
	}

	public Usuario getUsuarioCalculo() {
		return usuarioCalculo;
	}

	public void setUsuarioCalculo(Usuario usuarioCalculo) {
		this.usuarioCalculo = usuarioCalculo;
	}

	public Date getFechaCalculo() {
		return fechaCalculo;
	}

	public void setFechaCalculo(Date fechaCalculo) {
		this.fechaCalculo = fechaCalculo;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public String getNumColaborador() {
		return numColaborador;
	}

	public void setNumColaborador(String numColaborador) {
		this.numColaborador = numColaborador;
	}

	public Integer getIdSemana() {
		return idSemana;
	}

	public void setIdSemana(Integer idSemana) {
		this.idSemana = idSemana;
	}

}
