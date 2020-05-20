package com.sadss.csa.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sadss.csa.controller.beans.BitacoraUsuarioDTO;
import com.sadss.csa.controller.beans.UsuarioForm;
import com.sadss.csa.modelo.entidad.Usuario;
import com.sadss.csa.service.BitacoraUsuarioService;
import com.sadss.csa.service.UsuarioService;
import com.sadss.csa.util.SecurityUtils;
import com.sadss.csa.util.enums.Permiso;
import com.segurosargos.crece.modelo.entidad.Colaborador;
import com.segurosargos.crece.service.jerarquia.ColaboradoresService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private ColaboradoresService colaboradoresService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BitacoraUsuarioService bitacoraUsuarioService;
	
	/**
	 * Método principal para la carga de catálogos de usuarios
	 * @param model Modelo para alimentar información
	 * @return Vista de catálogo de usuarios
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String verUsuarios(ModelMap model) {
		feedDetalles(model);
		return "catalogo/usuarios/usuarios";
	}
	
	/**
	 * Método para el alta de usuarios
	 * @param model Modelo
	 * @return Vista para dar de alta usuario
	 */
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String altaUsuario(ModelMap model) {
		
		model.addAttribute("usuario", new UsuarioForm());
		obtenerInformacion(model);
		
		return "catalogo/usuarios/registro_actualizaUsuarios";
	}
	
	/**
	 * Método para modificar usuarios
	 * @param id Identificador del usuario
	 * @param model Modelo
	 * @return Vista para modificar usuario
	 */
	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editaUsuario(@RequestParam("id") int id, ModelMap model) {
		
		UsuarioForm uf = (new UsuarioForm().fromOrmModel(usuarioService.findOne(id), UsuarioForm.class));
		model.addAttribute("usuario", uf);
		obtenerInformacion(model);
		return "catalogo/usuarios/registro_actualizaUsuarios";
	}
	
	/**
	 * Método para guardar un usuario en alta y modificación
	 * @param uf UsuarioForm
	 * @param result BindingResult
	 * @param ra RedirectAttributes
	 * @return Vista principal de catálogo
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView guardarUsuario(@Valid @ModelAttribute("usuario") UsuarioForm uf,
			BindingResult result, RedirectAttributes ra) {
		
		ModelMap model = new ModelMap();
		
		if(result.hasErrors()) {
			model.addAttribute("usuario", uf);
			obtenerInformacion(model);
			LOG.debug("Errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/usuarios/registro_actualizaUsuarios", model);
		}
		
		String usuarioActual = SecurityUtils.getCurrentUser();
		Colaborador colab = colaboradoresService.consultaColaboradorPorNumero(uf.getNumColaborador());
		
		Usuario user = uf.toOrmModel(Usuario.class);
		user.setNombres(colab.getNombre());
		user.setaPaterno(colab.getApPaterno());
		user.setaMaterno(colab.getApMaterno());
		
		this.usuarioService.validateBeforeCreate(user, result);
		
		if(result.hasErrors()) {
			model.addAttribute("usuario", uf);

			obtenerInformacion(model);
			LOG.debug("Errores: " + result.getAllErrors());
			System.out.println("Errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/usuarios/registro_actualizaUsuarios", model);
		}
		
		if(user.getId() == null) {
			usuarioService.create(user);
			usuarioService.registrarAccionBitacora("ALTA DE USUARIO: " + user.getNumColaborador(), new Date(), usuarioActual);
			model.addAttribute("succmsg", "El colaborador ha sido registrado exitosamente");
		} else {
			usuarioService.update(user);
			usuarioService.registrarAccionBitacora("EDICIÓN DE USUARIO: " + user.getNumColaborador(), new Date(), usuarioActual);
			model.addAttribute("succmsg", "El colaborador ha sido modificado exitosamente");
		}
		
		feedDetalles(model);
		
		return new ModelAndView("catalogo/usuarios/usuarios", model);
	}

	/**
	 * Agrega la lista de los usuarios dados de alta en el sistema
	 * @param model Modelo
	 */
	public void feedDetalles(ModelMap model) {
		List<Usuario> usuarios = usuarioService.findAll();
		model.addAttribute("usuarios", usuarios);
		
		List<BitacoraUsuarioDTO> acciones = bitacoraUsuarioService.getRegistros();
		model.addAttribute("acciones", acciones);
	}
	
	/**
	 * Agrega información de colaboradores y permisos para el alta / modificación
	 * @param model Modelo
	 */
	public void obtenerInformacion(ModelMap model) {
		LinkedHashMap<String, String> colaboradores = usuarioService.getListColaboradores();
		model.addAttribute("colaboradores", colaboradores);
		
		model.addAttribute("permisos", Permiso.values());
	}
}
