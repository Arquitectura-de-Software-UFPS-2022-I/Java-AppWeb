package com.arquitectura.firmas.auth;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class ConstantsAuth {

	// Spring Security
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT
	public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public static final long TOKEN_EXPIRATION_TIME = 86_400_000;

}
