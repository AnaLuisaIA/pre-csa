package com.sadss.csa.util.enums;

public enum Permiso {
	
	//CATÁLOGOS
	CONSULTA_CAT("CONSULTA_CAT", "Consulta Catálogos"),
	EDITA_CAT("EDITA_CAT", "Edición de Catálogos"),
	CREA_CAT("CREA_CAT", "Creación de Catálogos"),
	
	//USUARIOS
	CONSULTA_USER("CONSULTA_USER", "Consulta Usuarios"),
	EDITA_USER("EDITA_USER","Edición de Usuarios"),
	CREA_USER("CREA_USER", "Creación de Usuarios"),
	
	//REPORTES
	CONSULTA_REPORTE("CONSULTA_REPORTE", "Consulta Reportes"),
	GENERA_REPORTE("GENERA_REPORTE", "Generación de Reportes"),
	
	//OPERACIONES
	GENERA_OP("GENERA_OP", "Generación de Operaciones"),
	CIERRA_CALCULO("CIERRA_CALCULO", "Cierre de Cálculo");
	

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
