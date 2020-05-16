package com.sadss.csa.controller.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.BitacoraVariables;

public class BitacoraVariablesForm extends GenericForm<BitacoraVariablesForm, BitacoraVariables> {

	@NotNull
	@NotEmpty
	@Size(max = 200)
	private String justificacion;
	private Integer idVariable;
	
	public BitacoraVariablesForm() {
		super();
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public Integer getIdVariable() {
		return idVariable;
	}

	public void setIdVariable(Integer idVariable) {
		this.idVariable = idVariable;
	}
	
	
	
	
}
