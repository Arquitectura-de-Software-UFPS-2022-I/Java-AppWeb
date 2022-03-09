package com.arquitectura.firmas;

import com.arquitectura.firmas.auth.EpsAuthenticationManager;
import com.arquitectura.firmas.auth.filter.JWTAuthenticationFilter;
import com.arquitectura.firmas.auth.filter.JWTAuthorizationFilter;
import com.arquitectura.firmas.auth.service.JWTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private CorsConfigurationSource corsConfigurationSource;

	@Autowired
	private EpsAuthenticationManager epsAuthenticationManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors()
				.configurationSource(corsConfigurationSource).and().csrf().disable().authorizeRequests().and()
				.authorizeRequests()
				.antMatchers("/user/**", "/user", "user", "/").permitAll().antMatchers(HttpMethod.POST, "/user/save")
				.permitAll().anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler).and()
				.addFilter(new JWTAuthenticationFilter(epsAuthenticationManager, jwtService))
				.addFilter(new JWTAuthorizationFilter(epsAuthenticationManager, jwtService));

		http.headers().frameOptions().disable();
	}

}
