package com.sadss.csa.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Controller encargado del login.
 * 
 *
 *
 */
@Controller
public class SecurityController {
	
//	@Resource
//	private UserUtil userUtil;
	
	/**
	 * Metodo de redireccionamiento a la pagina principal.
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(ModelMap  model, Principal principal ) {
		return "redirect:/";
	}
	
	/**
	 * Metodo de entrada al sistema.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap  model, Principal principal) {
		return "login";
	}
	
	/**
	 * Metodo de entrada al sistema.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap  model, Principal principal) {	
		return "redirect:/calculos/";
	}
	
	/**
	 * Metodo de entrada al sistema.
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "redirect:/login";
	}
	
	/**
	 * Metodo de manejo cuando el login ha fallado.
	 */
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginfailed(ModelMap  model) {
		model.addAttribute("error", "true");
		return "redirect:/login";
	}
	
	/**
	 * Metodo de manejo cuando el acceso ha sido denegado.
	 */
	@RequestMapping(value = "/denegado", method = RequestMethod.GET)
	public String denegado(ModelMap model) {
		model.addAttribute("error", "true");
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/lock", method = RequestMethod.GET)
	public String lock(ModelMap model) {
		
		return "lock";
	}
	
	/**
	 * Metodo para el error 404.
	 */
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String fourofour(ModelAndView model) {
		return "errors/404";
	}
	
}
