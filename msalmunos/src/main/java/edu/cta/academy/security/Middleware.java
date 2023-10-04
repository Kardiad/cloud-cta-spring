package edu.cta.academy.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/students")
public class Middleware implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Ida");
		HttpServletRequest r = (HttpServletRequest) request;
		System.out.println(r.getHeader("Authorization"));
		byte[] cadena = Base64.getDecoder().decode(r.getHeader("Authorization").split(" ")[1]);
		String clave = new String(cadena, StandardCharsets.UTF_8);
		System.out.println(clave);
		if(clave.equals("admin:admin123")) {
			chain.doFilter(request, response); //le dejamos pasar			
		}else {
			((HttpServletResponse) response).sendError(401, "No puedes pasar");;
		}
		System.out.println("Vuelta");
	}
	
}
