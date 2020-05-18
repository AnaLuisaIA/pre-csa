package com.sadss.csa.controller;

import java.net.URLDecoder;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
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
import com.sadss.csa.controller.beans.VariablesForm;
import com.sadss.csa.controller.beans.generic.FechaEditor;
import com.sadss.csa.modelo.entidad.Variable;
import com.sadss.csa.service.BitacoraVariablesService;
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

	/**
	 * Método principal
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String variableshome(ModelMap model) {
		feedLista(model);
		
		return "catalogo/variables/IMSS-INFONAVIT";
	}
	
	
	/**
	 * Metodo Para registrar una Variable
	 * */
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String altaVariable(ModelMap model, Principal principal) {
		
		agregarInformacion(model);
		model.addAttribute("variable", new VariablesForm());
		
		return "catalogo/variables/registro_actualizacionIMSS-INFONAVIT";
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
		
		String colaborador = SecurityUtils.getCurrentUser();
		
		variable.setNombre(variable.getNombre().toUpperCase());
		
		//Convierte el formulario al modelo
		Variable modelo = variable.toOrmModel(Variable.class);
		
		//Validaciones antes de agregar a BD
		this.variablesService.validateBeforeCreate(modelo, result);
		
		if(result.hasErrors()) {
			map.put("variable", variable);
			agregarInformacion(map);
			System.out.println("Existen errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/variables/registro_actualizacionIMSS-INFONAVIT", map);
		}

		if(modelo.getId() == null) {
			variablesService.create(modelo);
			variablesService.registrarAccionBitacora("ALTA DE VARIABLE: "+variable.getNombre() ,new Date() ,"-------", colaborador);
			ra.addFlashAttribute("succmsg", "Se creó correctamente el registro la Variable");
			
		}else {
			variablesService.update(modelo);
			variablesService.registrarAccionBitacora("MODIFICACIÓN DE VARIABLE: " +variable.getNombre() ,new Date() ,"--------", colaborador);
			ra.addFlashAttribute("succmsg", "Se actualizó correctamente la Variable");
		}
		
		return new ModelAndView("redirect:/variables/");
		
	}

	/**
	 * Método para eliminar una variable
	 * */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteVariable( @ModelAttribute("variable") @RequestParam("id") int id, 
			@RequestParam("justificacionIMSSForm") String justificacionIMSSForm, 
			RedirectAttributes ra) {
		
		Variable var  = variablesService.findOne(id);	
		String colaborador = SecurityUtils.getCurrentUser();
		
		System.out.println("justificacion " +justificacionIMSSForm);
		
		//Registramos en bitácora la eliminación
		variablesService.registrarAccionBitacora("VARIABLE ELIMINADA: "+ var.getNombre() ,new Date() , justificacionIMSSForm , colaborador);
		//Eliminamos variable
		variablesService.deleteById(id);
		
		ra.addFlashAttribute("succmsg", "La variable a sido eliminada con exito");
		
		return "redirect:/variables/";
	}
	
	/*
	 * Método para editar  una Variable
	 * @param id (id de la variable)
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
	 * Método para editar  el estado de Variable
	 * @param id (id de la variable)
	 * @param model (Modeo vacío)
	 *
	 * */
	@RequestMapping(value = "/editarEstado", method = RequestMethod.GET)
	public String editarEstadoVariable(@RequestParam(required =  true) int id, 
			@RequestParam("justificacionIMSSForm") String justificacionIMSSForm, 
			ModelMap model, RedirectAttributes ra) {

		Variable var  = variablesService.findOne(id);
		var.setEstado(!var.getEstado());
		variablesService.update(var);
		
		String colaborador = SecurityUtils.getCurrentUser();
		System.out.println("Justificación: " + justificacionIMSSForm);
		
		//agregar cambio a bitácora sin justificación
		variablesService.registrarAccionBitacora("MODIFICACIÓN DE ESTADO DE VARIABLE: " + var.getNombre() ,new Date() ,justificacionIMSSForm, colaborador);
		//enviar mensaje de que la variable está habilitada
		ra.addFlashAttribute("succmsg","Se cambio correctamente el estado de la Variable");

		return "redirect:/variables/";
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
	 * Obtiene los listados de las variables de la base y acciones registradas en bitácora
	 * @param model
	 */
	public void feedLista(ModelMap model) {
		List<Variable> variable = variablesService.findAll();
		model.put("variable", variable);
		
		List<BitacoraVariableDTO> acciones = bcService.getRegistros();
		model.put("acciones", acciones);
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

}
