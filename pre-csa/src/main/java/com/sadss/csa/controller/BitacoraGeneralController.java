package com.sadss.csa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sadss.csa.controller.beans.generic.FechaEditor;
import com.sadss.csa.modelo.entidad.Bitacora;
import com.sadss.csa.service.BitacoraSistemaService;

@Controller
@RequestMapping("bitacora")
public class BitacoraGeneralController {

	@Autowired
	private BitacoraSistemaService bitacoraService;
	
	/*
	 * Método principal para listar los registros en la Bitácora
	 * **/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listarBitacora(ModelMap model, HttpServletRequest request) {
		List<Bitacora> registros = bitacoraService.getAllBitacoras();
		model.put("acciones", registros);
		model.put("bitacora", new Bitacora());
		return "bitacora";
	}
	
	/**
	 * Método de busqueda de registros de acuerdoa  los filtros
	 * */
	private void fillList(ModelMap model, Bitacora b) {
		if(b != null) {
			List<Bitacora> acciones = bitacoraService.getBitacorasPorBusqueda(b);
			model.put("acciones", acciones);
		}
	}
	
	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new FechaEditor(new SimpleDateFormat("dd/MM/yyyy")));
	}
	
	/*
	 * Método de busqueda de acuerd a los filtros de fechas
	 * **/
	@RequestMapping(value = "/buscarBitacora",method = RequestMethod.POST)
	public ModelAndView busquedaBitacora(@Valid @ModelAttribute("bitacora") Bitacora b, BindingResult resul, HttpServletRequest request, RedirectAttributes ra) {
		ModelMap model = new ModelMap();
		fillList(model, b);
		model.put("bitacora", b);
		return new ModelAndView("bitacora",model);
	}
}

