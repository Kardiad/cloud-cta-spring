package edu.cta.academy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder,
			UserDetailsService userDetailsService) throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder).and().parentAuthenticationManager(null)// no use la cadena de Autenticadores
				.build();
	}
	

	@Bean //este bean es "la fuente" de los usuarios registrados
	public UserDetailsService userDetailsService (PasswordEncoder pe)//este objeto pe, es el de la línea 21
	{
		InMemoryUserDetailsManager manager = null;
		
			//definimos las crendeciales de los usuarios de nuestra app/servicio
			manager = new InMemoryUserDetailsManager();
			manager.createUser(User.withUsername("admin").password(pe.encode("admin123")).roles().build());
		
		return manager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationManager athenticationManager ) throws Exception {
		return httpSecurity
				.csrf().disable()//SIN ESTADO, SIN COOKIES, NO LA NECESITAMOS. EXIGIMOS TOKEN
				.cors().disable()
				.authorizeHttpRequests((authorize) -> authorize
				        .anyRequest().authenticated()
				    )
				.httpBasic()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.build();
	}
}
