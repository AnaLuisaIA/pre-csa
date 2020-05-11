package com.sadss.csa.util.enums;

public enum TipoNomina {
	
	SMA("SMA", "Semanal"),
	QUI("QUI", "Quincenal"),
	MEN("MEN", "Mensual"),
	BIM("BIM", "Bimestral"),
	TRI("TRI", "Trimestral"),
	SME("SME", "Semestral"),
	ANU("ANU", "Anual");
	
	private String value;
	private String label;
	
	private TipoNomina(String value, String label) {
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
