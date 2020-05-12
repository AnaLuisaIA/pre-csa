package com.sadss.csa.controller.beans;

import javax.validation.constraints.NotNull;

public class BusquedaCalendarioForm {
	
	@NotNull
	private Integer filtroAnio;

	public BusquedaCalendarioForm() {
		super();
	}

	public Integer getFiltroAnio() {
		return filtroAnio;
	}

	public void setFiltroAnio(Integer filtroAnio) {
		this.filtroAnio = filtroAnio;
	}


	
}
