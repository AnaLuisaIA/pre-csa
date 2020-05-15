package com.sadss.csa.util.enums;

import java.util.ArrayList;
import java.util.List;


public enum TipoPeriodo {
	
	PER("PER", "Por Periodo"), 
	EJE("EJE", "Por Ejercicio");

	private String value;
	private String label;

	private TipoPeriodo(String value, String label) {
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
	
	public static List<TipoPeriodo> valuesMenu(){
		
		List<TipoPeriodo> vals = new ArrayList<TipoPeriodo>();
		for (TipoPeriodo t : TipoPeriodo.values()) {
			 vals.add(t);
		}
		
		return vals;
		
	}

}
