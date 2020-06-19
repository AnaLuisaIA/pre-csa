package com.sadss.csa.controller;

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

	private static final Logger LOG = LoggerFactory.getLogger(VariablesController.class);

	/**
	 * MÃ©todo principal
	 * 
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
	 * Vista de Alta de Variable
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String altaVariable(ModelMap model) {
		agregarInformacion(model);
		VariablesForm variable = new VariablesForm();
		model.addAttribute("variable", variable);
		return "catalogo/variables/registro_actualizacionIMSS-INFONAVIT";
	}

	/**
	 * Vista para Editar una Variable
	 * 
	 * @param idvariable
	 * @param idPeriodo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editarVariable(@RequestParam(required = true) Integer idvariable,
			@RequestParam(required = true) Integer idPeriodo, ModelMap model) {

		Variable variable = variablesService.findOne(idvariable);
		PeriodoVariable pv = pvService.findOne(idPeriodo);

		if (variable == null && pv == null) {
			return "redirect:/variables/";
		}

		VariablesForm variableForm = (new VariablesForm().fromOrmModel(variable, VariablesForm.class));
		variableForm.setValor(pv.getValor());
		variableForm.setValorn(pv.getValor());
		variableForm.setFechaAplicacion(pv.getFechaAplicacion());
		variableForm.setFechaAplicacionn(pv.getFechaAplicacion());
		variableForm.setFechaTermino(pv.getFechaTermino());
		variableForm.setEstado(pv.getEstado());
		variableForm.setId(idvariable);
		variableForm.setIdPeriodo(idPeriodo);
		model.addAttribute("variable", variableForm);

		agregarInformacion(model);
		
		return "catalogo/variables/registro_actualizacionIMSS-INFONAVIT";
	}

	/**
	 * Guarda / Modifica una Variable
	 * 
	 * @param variable VariablesForm
	 * @param pv       PeriodoVariableForm
	 * @param result
	 * @param request
	 * @param ra
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView agregarVariable(@Valid @ModelAttribute("variable") VariablesForm variable,
			@Valid PeriodoVariablesForm pv, BindingResult result, HttpServletRequest request, RedirectAttributes ra) {

		ModelMap map = new ModelMap();

		if (result.hasErrors()) {
			map.put("variable", variable);
			agregarInformacion(map);
			LOG.info("Existen errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/variables/registro_actualizacionIMSS-INFONAVIT", map);
		}

		String colaborador = SecurityUtils.getCurrentUser();
		variable.setNombre(variable.getNombre().toUpperCase());

		Variable modeloVariable = variable.toOrmModel(Variable.class);
		PeriodoVariable modeloPeriodo = pv.toOrmModel(PeriodoVariable.class);

		// NUEVA VARIABLE
		if (modeloVariable.getId() == null) {

			// Validaciones antes de agregar a BD
			if (variablesService.esVariableDuplicada(modeloVariable)) {
				map.put("variable", variable);
				agregarInformacion(map);
				map.put("infomsg", "La Variable ya existe en el catálogo.");
				LOG.info("Existen errores: " + result.getAllErrors());
				return new ModelAndView("catalogo/variables/registro_actualizacionIMSS-INFONAVIT", map);
			}

			this.variablesService.create(modeloVariable);

			variablesService.registrarAccionBitacoraG("Alta de Variable: " + variable.getNombre(), new Date(),
					colaborador);
			variablesService.registrarAccionBitacora("Alta de  Variable: " + variable.getNombre(), new Date(),
					variable.getJustificacion(), colaborador);

			modeloPeriodo.setEstado(variable.getEstado());
			modeloPeriodo.setVariable(modeloVariable);
			this.pvService.create(modeloPeriodo);

			map.put("succmsg", "Se creó correctamente el registro de la Variable");

		} else {
			System.out.println(modeloPeriodo.getValor().setScale(6));
			// SI EL VALOR DE LA VARIABLE NO CAMBIÓ NO SE GENERA UN NUEVO PERIODO
			if (modeloPeriodo.getValor() == variable.getValorn()) {

				this.variablesService.update(modeloVariable);

				modeloPeriodo.setFechaTermino(variable.getFechaTermino());
				modeloPeriodo.setValor(variable.getValor());
				modeloPeriodo.setVariable(modeloVariable);
				modeloPeriodo.setFechaAplicacion(variable.getFechaAplicacion());
				modeloPeriodo.setEstado(variable.getEstado());
				this.pvService.update(modeloPeriodo);

				variablesService.registrarAccionBitacoraG("Modificación de la Variable: " + variable.getNombre(),
						new Date(), colaborador);

				variablesService.registrarAccionBitacora("Modificación de la Variable: " + variable.getNombre(),
						new Date(), variable.getJustificacion(), colaborador);

			} else {
				variable.setId((modeloPeriodo.getId()));
				modeloPeriodo.setId(variable.getIdPeriodo());
				modeloPeriodo.setFechaTermino(new Date());
				modeloPeriodo.setFechaAplicacion(variable.getFechaAplicacionn());
				modeloPeriodo.setValor(variable.getValorn());
				modeloPeriodo.setVariable(modeloVariable);
				modeloPeriodo.setEstado(variable.getEstado());
				this.pvService.update(modeloPeriodo);

				variable.setId((modeloVariable.getId()));
				modeloPeriodo.setFechaAplicacion(variable.getFechaAplicacion());
				modeloPeriodo.setFechaTermino(null);
				modeloPeriodo.setValor(variable.getValor());
				modeloPeriodo.setEstado(variable.getEstado());
				this.pvService.create(modeloPeriodo);

				variablesService
						.registrarAccionBitacoraG(
								"Modificación en el valor de la Variable :" + variable.getNombre() + " , del  Valor: "
										+ variable.getValorn() + " al Valor: " + variable.getValor(),
								new Date(), colaborador);

				variablesService.registrarAccionBitacora(
						"Modificación en el valor de la Variable :" + variable.getNombre() + " , del  Valor: "
								+ variable.getValorn() + " al Valor: " + variable.getValor(),
						new Date(), variable.getJustificacion(), colaborador);
			}

			map.put("succmsg", "La Variable se modificó con éxito.");

		}

		agregarLista(map);
		feedDetalles(map);
		return new ModelAndView("catalogo/variables/IMSS-INFONAVIT", map);
	}

	/**
	 * Elimina Variable
	 * 
	 * @param idVariable
	 * @param justificacionIMSSForm
	 * @param ra
	 * @param vf                    VariablesForms
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteVariable(@ModelAttribute("variable") @RequestParam("idVariable") int idVariable,
			@RequestParam("justificacionIMSSForm") String justificacionIMSSForm, RedirectAttributes ra,
			VariablesForm vf) {
		int id = idVariable;
		Variable var = variablesService.findOne(id);

		var.setNombre(var.getNombre());
		String colaborador = SecurityUtils.getCurrentUser();
		System.out.println("justificacion " + justificacionIMSSForm);

		variablesService.registrarAccionBitacoraG("Variable Eliminada : " + var.getNombre(), new Date(), colaborador);
		variablesService.registrarAccionBitacora("Variable Eliminada: " + var.getNombre(), new Date(),
				justificacionIMSSForm, colaborador);

		variablesService.deleteById(id);

		ra.addFlashAttribute("succmsg", "La Variable ha sido eliminada con éxito.");
		return "redirect:/variables/";
	}

	/**
	 * Edita estatus de Variable
	 * 
	 * @param idPeriodo
	 * @param justificacionIMSSForm
	 * @param nombreV               Nombre Variable
	 * @param model
	 * @param ra
	 * @param vf
	 * @return
	 */
	@RequestMapping(value = "/editarEstado", method = RequestMethod.GET)
	public String editarEstadoVariable(@RequestParam("idPeriodo") int idPeriodo,
			@RequestParam("justificacionIMSSForm") String justificacionIMSSForm,
			@RequestParam("nombreV") String nombreV, ModelMap model, RedirectAttributes ra, VariablesForm vf) {
		int id = idPeriodo;
		PeriodoVariable pv = pvService.findOne(id);
		pv.setEstado(!pv.getEstado());
		pvService.update(pv);
		String colaborador = SecurityUtils.getCurrentUser();

		variablesService.registrarAccionBitacoraG("Cambio en el estado de la Variable: " + nombreV, new Date(),
				colaborador);
		variablesService.registrarAccionBitacora("Cambio en el estado de la Variable: " + nombreV, new Date(),
				justificacionIMSSForm, colaborador);

		ra.addFlashAttribute("succmsg", "El estado de la Variable ha cambiado.");

		return "redirect:/variables/";
	}

	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new FechaEditor(new SimpleDateFormat("dd/MM/yyyy")));
	}

	/*
	 * Consulta bitacora
	 */
	public void feedDetalles(ModelMap model) {
		List<BitacoraVariableDTO> acciones = bcService.getRegistros();
		model.put("acciones", acciones);
	}

	/**
	 * Lista Tipos de Variables
	 * 
	 * @param model
	 */
	private void agregarInformacion(ModelMap model) {

		LinkedHashMap<String, String> tipovariable = new LinkedHashMap<String, String>();
		for (TipoVariable tv : TipoVariable.values()) {
			tipovariable.put(tv.getValue(), tv.getLabel());
		}
		model.put("tipovariable", tipovariable);

	}

	/**
	 * Mï¿½todo para encontrar los registros de variables
	 */
	private void agregarLista(ModelMap model) {
		List<Variable> variable = variablesService.findVariables();
		model.put("variable", variable);
	}

}
