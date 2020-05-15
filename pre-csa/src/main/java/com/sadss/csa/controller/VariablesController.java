package com.sadss.csa.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import com.sadss.csa.controller.beans.VariablesForm;
import com.sadss.csa.controller.beans.generic.FechaEditor;
import com.sadss.csa.modelo.entidad.Usuario;
import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.service.VariablesService;
import com.sadss.csa.util.SecurityUtils;
import com.sadss.csa.util.enums.TipoVariable;


@Controller
@RequestMapping("variable")
public class VariablesController {

	@Autowired
	private VariablesService variablesService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	/**
	 * Método principal
	 * @param model
	 * @param principal
	 * @return
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String variableshome(ModelMap model, Principal principal) {
		agregarLista(model);
		return "catalogo/variables/IMSS-INFONAVIT";
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
		List<Variable> variable = variablesService.findAll();
		System.out.println("Lista:" + variable);
		model.put("variable", variable);
	}
	
	/**
	 * Metodo para Registar una Variable
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView agregarVarible(@Valid @ModelAttribute("variable") 
	VariablesForm variable, BindingResult result,HttpServletRequest request, RedirectAttributes ra) 
			throws ParseException {
		
		ModelMap map = new ModelMap();
		
		//Validar Formulario
		if(result.hasErrors()) {
			map.put("variable", variable);
			System.out.println("Existen errores: "+ result.getAllErrors());
			return new ModelAndView("catalogo/variables/registro_actualizacionIMSS-INFONAVIT",map);
		}
		
		String nombreUsuario = SecurityUtils.getCurrentUser();
		
		variable.setNombre(variable.getNombre().toUpperCase());
		
		//Convierte el formulario al modelo
		Variable modelo = variable.toOrmModel(Variable.class);
		
		//Validaciones antes de agregar a BD
		this.variablesService.validateBeforeCreate(modelo, result);
		if(result.hasErrors()) {
			map.put("variable", variable);
			agregarInformacion(map);
			System.out.println("Existen errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/variables/registro_actualizacionIMSS-INFONAVIT",map);
		}
		
		//Agregar a la Base de Datos
		if(modelo.getId() == null) {
			//Agregar codigo de bitacora
			//modelo.setFechaAplicacion(new Date());
			System.out.println("Fecha: "+ variable.getFechaAplicacion());
			this.variablesService.create(modelo);
			map.put("succmsg", "Se creó correctamente el registro la Variable");
		}else {
			modelo.setFechaAplicacion(new Date());
			this.variablesService.update(modelo);
			map.put("succmsg", "Se actualizo correctamente la Variable");
		}
		agregarLista(map);
		return new ModelAndView("catalogo/variables/IMSS-INFONAVIT",map);
		
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
	
	/**
	 * Conversión de los objetos que se encuentran en los formularios para evitar error.
	 * @param binder
	 */
	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new FechaEditor(new SimpleDateFormat("dd/MM/yyyy")));
	}

	/**
	 * Método para eliminar una variable
	 * */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deletePagos(@RequestParam("id") int id) {
		variablesService.deleteById(id);
		return "redirect:/variable/";
	}
	
	/*
	 * Método para editar  una Variable
	 * @param id (id del rol)
	 * @param model (Modeo vacío)
	 *
	 * */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editarVariable(@RequestParam(required =  true) Integer id, ModelMap model) {
		Variable variable = variablesService.findOne(id);
		
		if(variable == null) {
			return "catalogo/variables/IMSS-INFONAVIT";
		}
		
		VariablesForm variableForm = (new VariablesForm().fromOrmModel(variable, VariablesForm.class));
		model.addAttribute("variable",variableForm);
		agregarInformacion(model);
		return "catalogo/variables/registro_actualizacionIMSS-INFONAVIT";
	}
	
	/*
	 * Método para editar  una Variable
	 * @param id (id del rol)
	 * @param model (Modeo vacío)
	 *
	 * */
	@RequestMapping(value = "/editarEstado", method = RequestMethod.GET)
	public String editarEstadoVariable(@RequestParam(required =  true) Integer id, ModelMap model) {
		Variable variable = variablesService.updateVariable(id);
		if(variable == null) {
			return "catalogo/variables/IMSS-INFONAVIT";
		}
		
		VariablesForm variableForm = (new VariablesForm().fromOrmModel(variable, VariablesForm.class));
		model.addAttribute("variable",variableForm);
		agregarInformacion(model);
		return "redirect:/variable/";
	}
}
