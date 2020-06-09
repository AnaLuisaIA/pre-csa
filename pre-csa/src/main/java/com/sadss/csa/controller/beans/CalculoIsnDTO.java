package com.sadss.csa.controller.beans;

import java.math.BigDecimal;
import java.util.Date;

public class CalculoIsnDTO {

	private Integer id;
	private Integer claveAgente;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer localidad;
	private BigDecimal tasa;
	private Integer baseGravable;
	private Integer isnMensual;
	private Integer isnSemanal;
	private String numColaborador;
	private String nombres;
	private String aPaterno;
	private String aMaterno;
	private Date fechaCalculo;	
	
	public CalculoIsnDTO(Integer claveAgente, Date fechaInicio, Date fechaFin, Integer localidad, BigDecimal tasa,
			Integer baseGravable, Integer isnMensual, Integer isnSemanal, String nombres, String aPaterno,
			String aMaterno, Date fechaCalculo) {
		super();
		this.claveAgente = claveAgente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.localidad = localidad;
		this.tasa = tasa;
		this.baseGravable = baseGravable;
		this.isnMensual = isnMensual;
		this.isnSemanal = isnSemanal;
		this.nombres = nombres;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.fechaCalculo = fechaCalculo;
	}
	
	public CalculoIsnDTO(Integer claveAgente) {
		super();
		this.claveAgente = claveAgente;
	}

	public CalculoIsnDTO(Date fechaCalculo) {
		super();
		this.fechaCalculo = fechaCalculo;
	}

	public CalculoIsnDTO(String numColaborador, String nombres, String aPaterno, String aMaterno) {
		super();
		this.numColaborador = numColaborador;
		this.nombres = nombres;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
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
	public Date getFechaCalculo() {
		return fechaCalculo;
	}
	public void setFechaCalculo(Date fechaCalculo) {
		this.fechaCalculo = fechaCalculo;
	}

	public String getNumColaborador() {
		return numColaborador;
	}

	public void setNumColaborador(String numColaborador) {
		this.numColaborador = numColaborador;
	}
	
}
