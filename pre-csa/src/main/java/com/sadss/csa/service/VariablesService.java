package com.sadss.csa.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> a4fdaad0932b603d4b9dbe2a70839b6f4a42a494
import com.sadss.csa.controller.beans.VariablesDTO;
import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.Variable;


public interface VariablesService  extends CrudService<Variable>{
	
	
	/**
	 * Lista de variables
	 * */
	public List<Variable> findVariables();
	
	public PeriodoVariable findVariablesID(Integer id);
	
	
	public Variable updateVariable(int id);
	
	/*
	 * Metodo Registro bitacora
	 * */
	
	public void registrarAccionBitacora(String accion, Date fecha,String justificacion, String user);
<<<<<<< HEAD
=======

>>>>>>> a4fdaad0932b603d4b9dbe2a70839b6f4a42a494
	/*
	 * Metodo Registro bitacora General
	 * */
	
	public void registrarAccionBitacoraG(String accion, Date fecha, String user);
<<<<<<< HEAD
=======

>>>>>>> a4fdaad0932b603d4b9dbe2a70839b6f4a42a494
	
	public List<PeriodoVariable> getPeriodos() throws ParseException;

	public List<VariablesDTO> getVariablesCalculo(Date fechaAplicacion, Date fechaTermino);
<<<<<<< HEAD

=======
	
>>>>>>> a4fdaad0932b603d4b9dbe2a70839b6f4a42a494
}
