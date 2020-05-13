/**
 * 
 * Seguros Argos
 * @author Juan antonio silva flores
 *
 */
package com.sadss.csa.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.segurosargos.crece.modelo.entidad.Colaborador;
import com.segurosargos.crece.service.jerarquia.ColaboradoresService;
import com.sadss.csa.util.Mensaje;
import com.sadss.csa.util.NotificationComponent;
import com.segurosargos.util.passEncoder.Encripta;

@Controller
@RequestMapping("recovery")
public class PasswordRecoveryController {

	@Resource
	private ColaboradoresService colaboradoresService;

	@Resource
	private Encripta encripta;

	@Resource
	private ApplicationContext applicationContext;

	/**
	 * Metodo para inicializar el proceso de recuperación de contraseña.
	 */
	@ResponseBody
	@RequestMapping("initRecovery")
	public ModelAndView recoveryRequest(RedirectAttributes ra, @RequestParam(required = true) String numColab,
			HttpServletRequest request) {

		Colaborador colab = colaboradoresService.consultaColaboradorPorNumero(numColab);

		// Si el colaborador se encontró y tiene un correo registrado
		if (colab != null && colab.getCorreo() != null) {
			
			createEmail(colab.getCorreo(), request, ra, true, numColab);
			
		} else {
			
			return new ModelAndView("redirect:../login?error=failedRecovery");
		}

		return new ModelAndView("redirect:../login?scss=recoverySent");
	}

	/**
	 * Metodo para efectuar el reinicio de la contraseña.
	 */
	@RequestMapping("initRecovery/execute")
	public ModelAndView recoveryExecute(RedirectAttributes ra, @RequestParam(required = true) String numColab) {

		Colaborador colab = colaboradoresService.consultaColaboradorPorNumero(encripta.dencripta(numColab));

		colab = colaboradoresService.cambiaPassColaborador(colab, encripta.getRandomPassword());

		Mensaje mensaje = new Mensaje();
		mensaje.setTo(colab.getCorreo());
		mensaje.setApp("SAC");
		mensaje.setFrom("SAC");
		mensaje.setAsunto("Nueva contraseÃ±a ACCESO UNIVERSAL ARGOS");

		StringBuilder sb = new StringBuilder();
		sb.append("<p style=\"font-weight: bold;font-size:1.1em;\" >");
		sb.append("Esta es tu nueva contraseÃ±a para ACCESO SAC: <br>");
		sb.append(encripta.dencripta(colab.getPassword()));
		sb.append("</p>");

		mensaje.setBody(sb.toString());

		NotificationComponent notificacion = new NotificationComponent(mensaje, applicationContext);
		new Thread(notificacion).start();

		return new ModelAndView("redirect:../../login?scss=resetPassword");
	}

	private void createEmail(String email, HttpServletRequest request, RedirectAttributes ra, Boolean es_colaborador,
			String numColab) {

		try {

			Mensaje mensaje = new Mensaje();
			mensaje.setTo(email);
			mensaje.setApp("SAC");
			mensaje.setFrom("SAC");
			mensaje.setAsunto("Reset de contraseña ACCESO UNIVERSAL ARGOS / SAC");

			StringBuilder sb = new StringBuilder();
			sb.append("<p style=\"font-weight: bold;font-size:1.1em;\" >");
			sb.append("Si solicitaste un reinicio de contraseÃ±a, visita el siguiente enlace: <br>");
			sb.append(request.getRequestURL());
			sb.append("/execute?numColab=");
			sb.append(encripta.encripta(numColab));
			sb.append("<br> Si no solicitaste este correo, simplemente ignora este correo.");
			sb.append("</p>");

			mensaje.setBody(sb.toString());

			NotificationComponent notificacion = new NotificationComponent(mensaje, applicationContext);
			new Thread(notificacion).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
