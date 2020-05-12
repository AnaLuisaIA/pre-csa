package com.sadss.csa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/calendario")
public class CalendarioController {

	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String verCalendarios(Model model) {
		model.addAttribute("titulo", "Calendario");
		return "catalogo/calendario/calendario";
	}
}
