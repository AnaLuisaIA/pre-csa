package com.sadss.csa.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.segurosargos.crece.api.ColaboradorDAO;
import com.segurosargos.crece.modelo.entidad.Colaborador;
import com.sadss.csa.controller.beans.security.AppUser;
import com.sadss.csa.service.UsuarioService;
import com.segurosargos.util.passEncoder.Encripta;
import com.segurosargos.security.beans.RolesUsuario;

@Service("userDetailsServicecrece")
public class UserDetailServiceImpl implements UserDetailsService {

	@Resource
	private ColaboradorDAO colaboradorDAO;

	@Resource
	private Encripta encripta;

	@Resource
	private UsuarioService usuarioService;

	/**
	 * Metodo para acceder al usuario.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("loadUserByUsername : " + username);

		Colaborador colaborador = colaboradorDAO.authenticateUser(username);

		System.out.println("colaborador: " + colaborador);

		if (colaborador == null) {
			System.out.println("Colaborador no se encontró");
			throw new UsernameNotFoundException("Colaborador no se encontró");
		}

		String password = encripta.dencripta(colaborador.getPassword());
		System.out.println("password: " + password);

		// A partir de aquí se envía el usuario autenticado
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		// accede a los permisos configurados en el perfil local
		Iterator<String> permisos = usuarioService.getPermisosByUsername(username).iterator();
		while (permisos.hasNext()) {
			String nextPer = permisos.next();
			// System.out.println("permisos del usuario: " + nextPer);
			authorities.add(new SimpleGrantedAuthority(nextPer));
		}

		AppUser user = new AppUser(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);

		user.setRol(RolesUsuario.valueOf(colaborador.getRolID()));
		user.setNombreCompleto(colaborador.getNombre());
		user.setMail(colaborador.getCorreo());
		user.setIdUsuario(colaborador.getId());

		return user;
	}
}
