package com.sadss.csa.controller.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.BitacoraTasas;

public class BitacoraTasaForm  extends GenericForm<BitacoraTasaForm, BitacoraTasas>{

	@NotNull
	@NotEmpty
	@Size(max = 200)
	private String justificacion;
	private Integer idTasa;
	public BitacoraTasaForm() {
		super();
	}
	public String getJustificacion() {
		return justificacion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	public Integer getIdTasa() {
		return idTasa;
	}
	public void setIdTasa(Integer idTasa) {
		this.idTasa = idTasa;
	}
	
	
	
	
}
