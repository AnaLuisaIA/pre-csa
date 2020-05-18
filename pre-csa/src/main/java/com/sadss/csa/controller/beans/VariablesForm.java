package com.sadss.csa.controller.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.util.enums.TipoVariable;

public class VariablesForm extends GenericForm<VariablesForm, Variable>{

	private Integer id;
	
	@NotNull
	@NotEmpty
	@Size(max = 100)
	private String nombre;
	private String descripcion;
	private BigDecimal valor;
	private Date fechaAplicacion;
	private TipoVariable tipo;
	private Boolean estado;
	
	private String justificacion;
	
	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	SimpleDateFormat sd =new SimpleDateFormat();
	
	public VariablesForm() {
		
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
