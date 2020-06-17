package com.sadss.csa.controller.beans;

import org.springframework.web.multipart.MultipartFile;

import com.sadss.csa.controller.beans.generic.GenericForm;
import com.sadss.csa.modelo.entidad.Calendario;

public class CalendarioForm extends GenericForm<CalendarioForm, Calendario> {

	private MultipartFile archivo;
	private Integer anio;
	private String fileName;
	private boolean nuevo;

	public CalendarioForm() {
	}

	public MultipartFile getArchivo() {
		return archivo;
	}

	public void setArchivo(MultipartFile archivo) {
		this.archivo = archivo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

}
