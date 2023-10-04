package edu.cta.academy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * Configuramos seguridad Spring.
 * */
@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder paswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager autenticationManager() {
		return null;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return null;
	}
}
