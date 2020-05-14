package com.sadss.csa.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Clase que extrae diferentes elementos del usuario que tiene la sesión activa
 * @author Ana Luisa
 */
public class SecurityUtils {
	
	public static String getCurrentUser() {
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return null; //authentication.getName();
	}
	
}
