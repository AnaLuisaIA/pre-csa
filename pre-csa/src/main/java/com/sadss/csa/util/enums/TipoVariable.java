package com.sadss.csa.util.enums;

public enum TipoVariable {
	
	GENERICO("GEN", "Genérico"),
	PATRON("PAT", "Patrón"),
	TRABAJADOR("TRA", "Trabajador");
	
	private String value;
	private String label;
	
	private TipoVariable(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
