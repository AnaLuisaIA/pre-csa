package com.sadss.csa.util.enums;

public enum TipoNominaIMSS {

	NOR("NOR", "Normal"), 
	FIN("FIN", "Finiquito"), 
	PTU("PTU", "PTU"), 
	AGU("AGU", "Aguinaldo");

	private String value;
	private String label;

	private TipoNominaIMSS(String value, String label) {
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
