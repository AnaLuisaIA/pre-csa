package com.sadss.csa.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

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

@Controller
@RequestMapping("tasas")
public class TasaController {

	@Autowired
	private TasaService tasaService;

	@Autowired
	private BitacoraTasaService btService;

	private static final Logger LOG = LoggerFactory.getLogger(TasaController.class);

	/**
	 * Metodo Principal
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String tasaHome(ModelMap model) {
		feedDetalles(model);
		agregarLista(model);
		agregartipoNomina(model);
		return "catalogo/TSN/tasaSobreNomina";
	}

	/**
	 * M�todo Para agregar la lista de las ciudades
	 * 
	 * @param model Modelo
	 **/
	public void obtenerCiudades(ModelMap model) {
		LinkedHashMap<String, String> ciudad = tasaService.getListCiudades();
		model.addAttribute("ciudad", ciudad);
	}

	/**
	 * Vista de Alta de Tasa
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String altaTasa(ModelMap model, Principal principal) {
		agregartipoNomina(model);
		agregarTipoVariable(model);
		obtenerCiudades(model);
		TasaForm tasa = new TasaForm();
		model.addAttribute("tasa", tasa);
		return "catalogo/TSN/registro_actualizacionTSN";
	}

	/**
	 * Agrega / Actualiza Tasa
	 * @param tasa TasaForm
	 * @param result
	 * @param request
	 * @param ra
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView agregarTasa(@Valid @ModelAttribute("tasa") TasaForm tasa, BindingResult result,
			HttpServletRequest request, RedirectAttributes ra){

		ModelMap map = new ModelMap();
		String colaborador = SecurityUtils.getCurrentUser();

		tasa.setEstado(tasa.getEstado().toUpperCase());
		
		System.out.println(tasa.getOficinas().toString());
		String oficinaConcat = "";
		
		for(String oficina: tasa.getOficinas()) {
			if(oficinaConcat == "") {
				oficinaConcat = oficina;
			} else {
				oficinaConcat += ","+oficina;
			}
		}
		
		tasa.setOficina(oficinaConcat);
		TasaSobreNomina modelo = tasa.toOrmModel(TasaSobreNomina.class);

		this.tasaService.validateBeforeCreate(modelo, result);

		if (result.hasErrors()) {
			map.put("tasa", tasa);
			agregartipoNomina(map);
			agregarTipoVariable(map);
			obtenerCiudades(map);
			map.put("errmsg", "Tasa registrada");
			LOG.info("Existen errores: " + result.getAllErrors());

			return new ModelAndView("catalogo/TSN/registro_actualizacionTSN", map);
		}

		if (modelo.getId() == null) {
			this.tasaService.create(modelo);
			
			tasaService.registrarAccionBitacoraG("Registro Tasa: " + tasa.getEstado(), new Date(), colaborador);
			tasaService.registrarAccionBitacora("Registro Tasa: " + tasa.getEstado(), new Date(),
					tasa.getJustificacion(), colaborador);

			map.put("succmsg", "Se cre� correctamente el registro de la Tasa");
		}

		else {
			
			this.tasaService.update(modelo);
			
			tasaService.registrarAccionBitacoraG("Actualizaci�n Tasa: " + tasa.getEstado(), new Date(), colaborador);
			tasaService.registrarAccionBitacora("Modicaci�n Tasa " + tasa.getEstado(), new Date(),
					tasa.getJustificacion(), colaborador);

			map.put("succmsg", "Se actualiz� correctamente la Tasa");
		}

		feedDetalles(map);
		agregarLista(map);
		agregartipoNomina(map);
		return new ModelAndView("catalogo/TSN/tasaSobreNomina", map);
	}

	/**
	 * Eliminar Tasa
	 * @param id Identificador de Tasa
	 * @param justificacionTasaForm Justificaci�n
	 * @param ra
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deteTasa(@ModelAttribute("tasa") @RequestParam("id") int id,
			@RequestParam("justificacionTasaForm") String justificacionTasaForm, RedirectAttributes ra) {
		
		TasaSobreNomina tas = tasaService.findOne(id);
		tas.setEstado(tas.getEstado());
		String colaborador = SecurityUtils.getCurrentUser();
		// Registro Bitacora General (Eliminar Tasa)
		tasaService.registrarAccionBitacoraG("Tasa Eliminada: " + tas.getEstado(), new Date(), colaborador);
		// Registro Bitacora Tasa (Eliminar Tasa)
		tasaService.registrarAccionBitacora("Tasa Eliminada " + tas.getEstado(), new Date(), justificacionTasaForm,
				colaborador);
		// ELiminar Tasa
		tasaService.deleteById(id);
		ra.addFlashAttribute("succmsg", "La tasa a sido eliminada con exito");
		return "redirect:/tasas/";
	}

	/**
	 * Vista para edici�n de Tasas
	 * @param id Identificador de Tasa
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editarTasa(@RequestParam("id") int id, ModelMap model) {	
		
		TasaForm tasaForm = (new TasaForm().fromOrmModel(tasaService.findOne(id), TasaForm.class));
		
		Set<String> oficinasSet = new HashSet<String>();
		String[] oficinas = tasaForm.getOficina().split(",");
		
		for(String o: oficinas) {
			oficinasSet.add(o);
		}
		
		tasaForm.setOficinas(oficinasSet);
		
		model.addAttribute("tasa", tasaForm);
		
		agregartipoNomina(model);
		agregarTipoVariable(model);
		obtenerCiudades(model);
		
		return "catalogo/TSN/registro_actualizacionTSN";
	}

	/**
	 * M�todo para editar el Estado de una tasa
	 * 
	 * @param id                    (id de la tasa)
	 * @param justificacionTasaForm (Justificacion del cambio de estado de la tasa)
	 * @param model                 (Modelo vac�o)
	 */
	@RequestMapping(value = "/editarEstados", method = RequestMethod.GET)
	public String editarEstadoTasa(@RequestParam(required = true) int id,
			@RequestParam("justificacionTasaForm") String justificacionTasaForm, RedirectAttributes ra, TasaForm tf,
			ModelMap model) {
		
		TasaSobreNomina tas = tasaService.findOne(id);
		tas.setEstatus(!tas.getEstatus());
		tasaService.update(tas);
		String colaborador = SecurityUtils.getCurrentUser();

		tasaService.registrarAccionBitacora("Modifico el estatus de la tasa " + tas.getEstado(), new Date(),
				justificacionTasaForm, colaborador);
		
		ra.addFlashAttribute("succmsg", "Se cambio correctamente el estado de la tasa");

		return "redirect:/tasas/";
	}
	

	@InitBinder
	void intBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new FechaEditor(new SimpleDateFormat("dd/MM/yyyy")));
	}
	
	/*
	 * M�todo para encontrar los Registros de las Tasas
	 */

	private void agregarLista(ModelMap model) {
		List<TasaSobreNomina> tasa = tasaService.findTasas();
		model.put("tasa", tasa);
	}

	/*
	 * Consulta Bitacora Tasas
	 */
	public void feedDetalles(ModelMap model) {
		List<BitacoraTasaDTO> accionest = btService.getRegistros();
		model.put("accionest", accionest);
	}

	/*
	 * Listar el tipo de Nomina
	 * 
	 * @param model
	 */
	private void agregartipoNomina(ModelMap model) {
		LinkedHashMap<String, String> tipoNomina = new LinkedHashMap<String, String>();
		for (TipoNomina tn : TipoNomina.values()) {
			tipoNomina.put(tn.getValue(), tn.getLabel());
		}
		model.put("tipoNomina", tipoNomina);
	}

	/*
	 * Lista Tipo variable Tasa
	 * 
	 * @param Molde
	 */
	private void agregarTipoVariable(ModelMap model) {
		LinkedHashMap<String, String> tipoVariable = new LinkedHashMap<String, String>();
		for (TipoVariableTasa tv : TipoVariableTasa.values()) {
			tipoVariable.put(tv.getValue(), tv.getLabel());
		}
		model.put("tipoVariable", tipoVariable);
	}
}
