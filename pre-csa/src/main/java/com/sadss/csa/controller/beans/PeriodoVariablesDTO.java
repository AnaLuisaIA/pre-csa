package com.sadss.csa.controller.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.util.enums.TipoVariable;

public class PeriodoVariablesDTO {

	private Integer  id;
	private Integer idVariable;
	private String nombre;
	private String descripcion;
	private BigDecimal valor;
	private TipoVariable tipo;
	private Date fechaAplicacion;
	private Date fechaTermino;
	private Boolean estado;
	
	public PeriodoVariablesDTO(Integer id, Integer idVariable, String nombre, String descripcion, Boolean estado, BigDecimal valor,
			TipoVariable tipo, Date fechaAplicacion, Date fechaTermino) {
		super();
		this.id = id;
		this.idVariable = idVariable;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
		this.tipo = tipo;
		this.fechaAplicacion = fechaAplicacion;
		this.fechaTermino = fechaTermino;
		this.estado = estado;
	}

	

	public PeriodoVariablesDTO(Integer id, Integer idVariable, BigDecimal valor, Date fechaAplicacion) {
		super();
		this.id = id;
		this.idVariable = idVariable;
		this.valor = valor;
		this.fechaAplicacion = fechaAplicacion;
	}



	public PeriodoVariablesDTO() {
		super();
	}


	public Integer getIdVariable() {
		return idVariable;
	}


	public void setIdVariable(Integer idVariable) {
		this.idVariable = idVariable;
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

	public Date getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
}
