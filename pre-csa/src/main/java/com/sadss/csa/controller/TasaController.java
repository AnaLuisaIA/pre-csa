package com.sadss.csa.controller;

import java.security.Principal;
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

import com.sadss.csa.controller.beans.BitacoraTasaDTO;
import com.sadss.csa.controller.beans.TasaForm;
import com.sadss.csa.controller.beans.generic.FechaEditor;
import com.sadss.csa.modelo.entidad.TasaSobreNomina;
import com.sadss.csa.service.BitacoraTasaService;
import com.sadss.csa.service.TasaService;
import com.sadss.csa.util.SecurityUtils;
import com.sadss.csa.util.enums.TipoNomina;
import com.sadss.csa.util.enums.TipoVariableTasa;

import freemarker.core.ParseException;

@Controller
@RequestMapping("tasas")
public class TasaController {

	@Autowired
	private TasaService tasaService;
	
	@Autowired
	private BitacoraTasaService btService;
	
	private static final Logger Logger = LoggerFactory.getLogger(TasaController.class);
	
	/**
	 * Metodo Principal
	 * */
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String tasaHome(ModelMap model) {
		feedDetalles(model);
		agregarLista(model);
		return "catalogo/TSN/tasaSobreNomina";
	}
	
	/*
	 * Método para encontrar los Registros de las Tasas
	 * */
	
	private void agregarLista(ModelMap model) {
		List<TasaSobreNomina> tasa = tasaService.findTasas();
		model.put("tasa", tasa);
	}
	
	/*
	 * Consulta Bitacora Tasas
	 * */
	public void feedDetalles(ModelMap model) {
		List<BitacoraTasaDTO> accionest = btService.getRegistros();
		model.put("accionest", accionest);
	}
	
	/*
	 * Listar el tipo de Nomina
	 * @param model
	 * */
	private void agregartipoNomina(ModelMap model) {
		LinkedHashMap<String, String> tipoNomina = new LinkedHashMap<String, String>();
		for(TipoNomina tn: TipoNomina.values()) {
			tipoNomina.put(tn.getValue(), tn.getLabel());
		}
		model.put("tipoNomina", tipoNomina);
	}
	
	/*
	 * Lista Tipo variable Tasa
	 * @param Molde
	 * */
	private void agregarTipoVariable(ModelMap model) {
		LinkedHashMap<String, String> tipoVariable = new LinkedHashMap<String, String>();
		for(TipoVariableTasa tv: TipoVariableTasa.values()) {
			tipoVariable.put(tv.getValue(), tv.getLabel());
		}
		model.put("tipoVariable", tipoVariable);
	}
	/*
	 * Método para registrar una Tasa
	 * */
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String altaTasa(ModelMap model, Principal principal) {
		agregartipoNomina(model);
		agregarTipoVariable(model);
		TasaForm tasa = new TasaForm();
		model.addAttribute("tasa",tasa);
		return "catalogo/TSN/registro_actualizacionTSN";
	}
	
	/**
	 * Método Para registrar una Variable
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView agregarTasa(@Valid @ModelAttribute("tasa") TasaForm tasa, BindingResult result, 
			HttpServletRequest request, RedirectAttributes ra) throws ParseException {
		ModelMap map = new ModelMap();
		//Validar Formulario
		if(result.hasErrors()) {
			map.put("tasa", tasa);
			System.out.println("Exiten errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/TSN/registro_actualizacionTSN", map);
		}
		String colaborador = SecurityUtils.getCurrentUser();
		tasa.setEstado(tasa.getEstado().toUpperCase());
		//Convierte el formulario al modelo
		TasaSobreNomina modelo = tasa.toOrmModel(TasaSobreNomina.class);
		//Validaciones antes de agregar a la Base de datos
		this.tasaService.validateBeforeCreate(modelo, result);
		if(result.hasErrors()) {
			map.put("tasa", tasa);
			agregartipoNomina(map);
			agregarTipoVariable(map);
			System.out.println("Existen errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/TSN/registro_actualizacionTSN", map);
		}
		//Agregar a la Base de datos
		if(modelo.getId() == null) {
			modelo.setFechaAplicacion(new Date());
			tasaService.registrarAccionBitacora("Registro Tasa "+tasa.getEstado(), new Date(), "--------", colaborador);
			this.tasaService.create(modelo);
			map.put("succmsg", "Se creó correctamente el registro de la Tasa");
		}else {
			modelo.setFechaAplicacion(new Date());
			tasaService.registrarAccionBitacora("Modicación Tasa "+tasa.getEstado(), new Date(), tasa.getJustificacion(), colaborador);
			this.tasaService.update(modelo);
			map.put("succmsg", "Se actualizo correctamente de Tasa");
		}
		
		agregarLista(map);
		return new ModelAndView("catalogo/TSN/tasaSobreNomina", map);
	}
	
	/*
	 * Conversión de los objetos que se encuentran en los formunalrios para evitar error.
	 * @param binder
	 * */
	@InitBinder
	void intBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new FechaEditor(new SimpleDateFormat("dd/MM/yyyy")));
	}
	
	/*
	 * Método para eliminar una Tasa
	 * */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deteTasa(@ModelAttribute("tasa") @RequestParam("id") int id, @RequestParam("justificacionTasaForm") String justificacionTasaForm, RedirectAttributes ra, TasaForm tf) {
		TasaSobreNomina tas = tasaService.findOne(id);
		tas.setEstado(tas.getEstado());
		String colaborador = SecurityUtils.getCurrentUser();
		//Registro Bitacora
		tasaService.registrarAccionBitacora("Tasa Eliminada " + tas.getEstado(), new Date(), justificacionTasaForm, colaborador);
		//ELiminar Tasa
		tasaService.deleteById(id);
		ra.addFlashAttribute("succmsg", "La tasa a sido eliminada con exito");
		return "redirect:/tasa/";
	}
	
	/*
	 * Metodo Para editar una Tasa
	 * @param id(id de la tasa)
	 * @param model (Modelo vacío)
	 * */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editarTasa(@RequestParam(required = true) Integer id, ModelMap model) {
		TasaSobreNomina tasa = tasaService.findOne(id);
		if(tasa == null) {
			return "catalogo/TSN/tasaSobreNomina";
		}
		TasaForm tasaForm = (new TasaForm().fromOrmModel(tasa, TasaForm.class));
		model.addAttribute("tasa",tasaForm);
		agregartipoNomina(model);
		agregarTipoVariable(model);
		return "catalogo/TSN/registro_actualizacionTSN";
	}
	
	/**
	 * Método para editar el Estado de una tasa
	 * @param id (id de la tasa)
	 * @param justificacionTasaForm (Justificacion del cambio de estado de la tasa)
	 * @param model (Modelo vacío)
	 * */
	@RequestMapping(value = "/editarEstado", method = RequestMethod.GET)
	public String editarEstadoTasa(@RequestParam(required = true) int id, @RequestParam("justificacionTasaForm") String justificacionTasaForm, RedirectAttributes ra, TasaForm tf, ModelMap model) {
		ModelMap map = new ModelMap();
		System.out.print("id" + tasaService.findOne(id));
		TasaSobreNomina tas = tasaService.findOne(id);
		tas.setEstatus(!tas.getEstatus());
		tasaService.update(tas);
		String colaborador = SecurityUtils.getCurrentUser();
		TasaSobreNomina tasa = tasaService.updateTasa(id);
		//Agregar cambios a bitacora
		tasaService.registrarAccionBitacora("Modifico el estatus de la tasa "+ tas.getEstado(), new Date(), justificacionTasaForm, colaborador);
		ra.addFlashAttribute("succmsg", "Se cambio correctamnet el estado de la tasa");
		TasaForm tasaFom = (new TasaForm().fromOrmModel(tasa, TasaForm.class));
		return "redirect:/tasa/";
	}
}

