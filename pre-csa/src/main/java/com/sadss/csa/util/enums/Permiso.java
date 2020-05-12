package com.sadss.csa.util.enums;

public enum Permiso {
	
	//CAT�LOGOS
	CONSULTA_CAT("CONSULTA_CAT", "Consulta Cat�logos"),
	EDITA_CAT("EDITA_CAT", "Edici�n de Cat�logos"),
	CREA_CAT("CREA_CAT", "Creaci�n de Cat�logos"),
	
	//USUARIOS
	CONSULTA_USER("CONSULTA_USER", "Consulta Usuarios"),
	EDITA_USER("EDITA_USER","Edici�n de Usuarios"),
	CREA_USER("CREA_USER", "Creaci�n de Usuarios"),
	
	//REPORTES
	CONSULTA_REPORTE("CONSULTA_REPORTE", "Consulta Reportes"),
	GENERA_REPORTE("GENERA_REPORTE", "Generaci�n de Reportes"),
	
	//OPERACIONES
	GENERA_OP("GENERA_OP", "Generaci�n de Operaciones"),
	CIERRA_CALCULO("CIERRA_CALCULO", "Cierre de C�lculo");
	

	private String value;
	private String descripcion;
	
	private Permiso(String value, String descripcion) {
		this.value = value;
		this.descripcion = descripcion;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
