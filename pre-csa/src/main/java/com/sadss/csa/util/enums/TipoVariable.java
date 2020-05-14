package com.sadss.csa.util.enums;

import java.util.ArrayList;
import java.util.List;

public enum TipoVariable {
	
	GENERICO("GEN", "Gen�rico"),
	PATRON("PAT", "Patr�n"),
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
	
	public static List<TipoVariable> valuesMenu(){
		
		List<TipoVariable> vals = new ArrayList<TipoVariable>();
		for (TipoVariable t : TipoVariable.values()) {
			 vals.add(t);
		}
		
		return vals;
		
	}
}
