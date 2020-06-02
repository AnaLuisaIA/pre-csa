package com.sadss.csa.controller;

import java.security.Principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.sadss.csa.controller.beans.BitacoraVariableDTO;
import com.sadss.csa.controller.beans.PeriodoVariablesForm;
import com.sadss.csa.controller.beans.VariablesForm;
import com.sadss.csa.controller.beans.generic.FechaEditor;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.service.BitacoraVariablesService;
import com.sadss.csa.service.PeriodoVariableService;
import com.sadss.csa.service.VariablesService;
import com.sadss.csa.util.SecurityUtils;
import com.sadss.csa.util.enums.TipoVariable;


@Controller
@RequestMapping("variables")
public class VariablesController {

	@Autowired
	private VariablesService variablesService;
	
	@Autowired
	private BitacoraVariablesService bcService;
	
	@Autowired
	private PeriodoVariableService pvService;
	
	private static final Logger logger = LoggerFactory.getLogger(VariablesController.class);
	/**
	 * Método principal
	 * @param model
	 * @param principal
	 * @return
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String variableshome(ModelMap model) {
		feedDetalles(model);
		agregarLista(model);
		return "catalogo/variables/IMSS-INFONAVIT";
	}
	

	/**
	 * Lista Tipos de Variables
	 * @param model
	 */
	private void agregarInformacion(ModelMap model) {
		
		LinkedHashMap<String, String> tipovariable = new LinkedHashMap<String, String>();
		for(TipoVariable tv: TipoVariable.values()) {
			tipovariable.put(tv.getValue(), tv.getLabel());
		}
		model.put("tipovariable", tipovariable);
		
	}
	
	/*
	 * Consulta bitacora
	 * */
	public void feedDetalles(ModelMap model) {
		List<BitacoraVariableDTO> acciones = bcService.getRegistros();
		model.put("acciones", acciones);
	}
	
	/**
	 * Conversión de los objetos que se encuentran en los formularios para evitar error.
	 * @param binder
	 */
	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new FechaEditor(new SimpleDateFormat("dd/MM/yyyy")));
	}
	
	/**
	 * Metodo Para registrar una Variable
	 * */
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String altaVariable(ModelMap model, Principal principal) {
		agregarInformacion(model);
		VariablesForm variable = new VariablesForm();
		model.addAttribute("variable",variable);
		return "catalogo/variables/registro_actualizacionIMSS-INFONAVIT";
	}
	/**
	 * Método para encontrar los registros de variables
	 * */
	private void agregarLista(ModelMap model) {
		List<Variable> variable = variablesService.findVariables();
		model.put("variable", variable);
	}
	
	/*
	 * Método para editar  una Variable
	 * @param id (id de la variable)
	 * @param model (Modeo vacío)
	 *
	 * */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editarVariable(@RequestParam(required =  true) Integer idvariable,@RequestParam(required =  true) Integer idPeriodo, ModelMap model) {
		//Busca la variable atravez del idVariable
		Variable variable =  variablesService.findOne(idvariable);
		//Buscar el periodo atraves del idPeriodo
		PeriodoVariable pv = pvService.findOne(idPeriodo);
		//Se comprueva si la variable y el periodo no estan en nulos
		if(variable == null && pv == null) {
			//Si es nulo se redirecciona a la pagina principal de Variables
			return "redirect:/variables/";
		}
		//Se convierte en un formulario
		VariablesForm variableForm = (new VariablesForm().fromOrmModel(variable, VariablesForm.class));
		//Se asigna el Valor de periodo al formulario
		variableForm.setValor(pv.getValor());
		//Se asigan el valor a un input no visible para hacer una comparación
		variableForm.setValorn(pv.getValor());
		//Se asigna la fecha de Aplicación de periodo al formulario
		variableForm.setFechaAplicacion(pv.getFechaAplicacion());
		//Se asigan la fecha aplicación a un input no visible
		variableForm.setFechaAplicacionn(pv.getFechaAplicacion());
		variableForm.setId(idvariable);
		variableForm.setIdPeriodo(idPeriodo);
		model.addAttribute("variable",variableForm);
		agregarInformacion(model);
		return "catalogo/variables/registro_actualizacionIMSS-INFONAVIT";
	}
	
	/**
	 * Metodo para Registar una Variable
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView agregarVariable(@Valid @ModelAttribute("variable") 
	VariablesForm variable,@Valid PeriodoVariablesForm pv, BindingResult result,HttpServletRequest request, RedirectAttributes ra) 
			throws ParseException {
		ModelMap map = new ModelMap();
		
		//Validar Formulario
		if(result.hasErrors()) {
			map.put("variable", variable);
			System.out.println("Existen errores: "+ result.getAllErrors());
			return new ModelAndView("catalogo/variables/registro_actualizacionIMSS-INFONAVIT",map);
		}
		
		String colaborador = SecurityUtils.getCurrentUser();
		
		variable.setNombre(variable.getNombre().toUpperCase());
		//Convierte el formulario al modelo
		Variable modelo = variable.toOrmModel(Variable.class);
		PeriodoVariable model = pv.toOrmModel(PeriodoVariable.class);
		
		//Validaciones antes de agregar a BD
		
		if(result.hasErrors()) {
			map.put("variable", variable);
			agregarInformacion(map);
			System.out.println("Existen errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/variables/registro_actualizacionIMSS-INFONAVIT",map);
		}
		//Agregar a la Base de Datos
		if(modelo.getId() == null) {
			//Valida si existe un resgistro ya creado
			this.variablesService.validateBeforeCreate(modelo, result);
			//Registro en bitacora Variables
			variablesService.registrarAccionBitacora("Registro variable "+variable.getNombre() ,new Date() ,variable.getJustificacion(), colaborador);
			//Registro en tabla Variables
			this.variablesService.create(modelo);
			//Asignar el id de la variable registrada
			variable.setId((modelo.getId()));
			//Se envia el modelo de variables al modelo de periodo variables
			model.setVariable(modelo);
			//registrar en tabla periodo variable
			this.pvService.create(model);
			map.put("succmsg", "Se creó correctamente el registro la Variable");
		}else {
			if(model.getValor().equals(variable.getValorn())) {
				//Registro en bitacora variables
				variablesService.registrarAccionBitacora("Modifico la variable " +variable.getNombre() ,new Date() ,variable.getJustificacion(), colaborador);
				//Modifica datos de variable
				this.variablesService.update(modelo);
			}else {
				//Registro en bitacora variables
				variablesService.registrarAccionBitacora("Registro de un nuevo periodo de variable: " +variable.getNombre() ,new Date() ,variable.getJustificacion(), colaborador);
				//Variable Id
				variable.setId((modelo.getId()));
				//Periodo id
				model.setId(variable.getIdPeriodo());
				//Se envia la fecha termino
				model.setFechaTermino(new Date());
				//Se envia la fecha Aplicación
				model.setFechaAplicacion(variable.getFechaAplicacionn());
				//se envia el valor anterior
				model.setValor(variable.getValorn());
				//Se envia el formulario variables a periodo 
				model.setVariable(modelo);
				//Se modifica el registro
				this.pvService.update(model);
				//variable id
				variable.setId((modelo.getId()));
				//Fecha aplicación
				model.setFechaAplicacion(variable.getFechaAplicacion());
				//Periodo id
				model.setId(null);
				//Fecha fin periodo 
				model.setFechaTermino(null);
				//valor nuevo
				model.setValor(variable.getValor());
				//se crea el nuevo registro de periodo variables
				this.pvService.create(model);
			}
			map.put("succmsg", "Se actualizo correctamente la Variable");		
		}
		agregarLista(map);
		return new ModelAndView("catalogo/variables/IMSS-INFONAVIT",map);
	}

	/**
	 * Método para eliminar una variable
	 * */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteVariable( @ModelAttribute("variable") @RequestParam("idVariable") int idVariable,@RequestParam("justificacionIMSSForm") String justificacionIMSSForm,RedirectAttributes ra, VariablesForm vf) {
		int id = idVariable;
		Variable var  = variablesService.findOne(id);
		
		var.setNombre(var.getNombre());
		String colaborador = SecurityUtils.getCurrentUser();
		System.out.println("justificacion " +justificacionIMSSForm);
		//Registramos en bitácora la eliminación
		variablesService.registrarAccionBitacora("Variable Eliminada "+ var.getNombre() ,new Date() , justificacionIMSSForm , colaborador);
		//Eliminamos variable
		variablesService.deleteById(id);
		
		ra.addFlashAttribute("succmsg", "La variable a sido eliminada con exito");
		return "redirect:/variables/";
	}
	
	/*
	 * Método para editar  el estado de Variable
	 * @param id (id de la variable)
	 * @param model (Modeo vacío)
	 *
	 * */
	@RequestMapping(value = "/editarEstado", method = RequestMethod.GET)
	public String editarEstadoVariable(@RequestParam(required =  true) int idVariable,@RequestParam("justificacionIMSSForm") String justificacionIMSSForm ,ModelMap model, RedirectAttributes ra, VariablesForm vf) {
		int id = idVariable;
		Variable var  = variablesService.findOne(id);
		var.setEstado(!var.getEstado());
		variablesService.update(var);
		String colaborador = SecurityUtils.getCurrentUser();
		Variable variable = variablesService.updateVariable(id);
		//agregar cambio a bitácora sin justificación
		variablesService.registrarAccionBitacora("Modifico el estado de la Variable " + var.getNombre() ,new Date() ,justificacionIMSSForm, colaborador);
		//enviar mensaje de que la variable está habilitada
		ra.addFlashAttribute("succmsg","Se cambio correctamente el estado de la Variable");
		VariablesForm variableForm = (new VariablesForm().fromOrmModel(variable, VariablesForm.class));
		return "redirect:/variables/";
	}

}
