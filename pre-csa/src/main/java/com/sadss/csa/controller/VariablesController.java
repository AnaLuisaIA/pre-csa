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
		List<Variable> variableModelo = variablesService.findAll();
		System.out.println("Lista:" + variableModelo);
		model.put("variableModelo", variableModelo);
	}
	
	/**
	 * Metodo para Registar una Variable
	 * */
	@RequestMapping(value = "/saveVariable", method = RequestMethod.POST)
	public ModelAndView agregarVarible(@Valid VariablesForm variable, BindingResult result,HttpServletRequest request, RedirectAttributes ra) throws ParseException {
				
		System.out.println("Datos"+variable);
		
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
		//Variable modelo = variable.toOrmModel(Variable.class);
		Variable vf = new Variable();
		
		//Validaciones antes de agregar a BD
		this.variablesService.validateBeforeCreate(vf, result);
		if(result.hasErrors()) {
			map.put("variable", variable);
			agregarInformacion(map);
			System.out.println("Existen errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/variables/registro_actualizacionIMSS-INFONAVIT",map);
		}
		
		//Agregar a la Base de Datos
		if(vf.getId() == null) {
			variablesService.create(vf);
			System.out.println("Datos"+variable);
			map.put("succmsg", "Se creó correctamente el registro del rol");
		}else {
			vf.setFechaAplicacion(new Date());
			this.variablesService.update(vf);
			map.put("succmsg", "Se actualizo correctamente el Rol");
		}
		agregarLista(map);
		return new ModelAndView("catalogo/variables/registro_actualizacionIMSS-INFONAVIT",map);
		
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
	
}
