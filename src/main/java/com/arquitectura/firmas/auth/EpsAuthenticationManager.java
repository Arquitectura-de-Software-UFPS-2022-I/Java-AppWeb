package com.arquitectura.firmas.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import models.UserDto;
import services.impl.ApiService;

@Service
public class EpsAuthenticationManager implements AuthenticationManager {


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		@SuppressWarnings("unchecked")
		HashMap<String, Object> details = (HashMap<String, Object>) authentication.getDetails();

		UserDto respuesta = null;
		try {
			respuesta = new ApiService().getUserService().loginUser(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (respuesta == null) {
			throw new BadCredentialsException("Datos de ingreso invalidos");
		}
		if (respuesta.getUsername() == null) {
			throw new BadCredentialsException("Datos de ingreso invalidos");			
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority("ADMIN"));

		if (authorities.isEmpty()) {
			throw new BadCredentialsException("Sin roles asignados");
		}

		details = new HashMap<>();
		details.put("nombre", respuesta.getFull_name());
		details.put("username", respuesta.getUsername());

		return new EpsAuthenticationToken(username, null, details, authorities);
	}

}
