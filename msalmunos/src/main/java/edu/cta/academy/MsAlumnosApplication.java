package edu.cta.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
 * EurekaClient necesario para listar en eureka y posteriormente para el gateway
 * SpringbootApplication es para decir que es una aplicación Spring
 * @EntityScan es necesario para que pueda escanear los recursos
 * @ComponentScan es algo vuiejo qu seguramente no tenga que usar, pero no debería
 * */

@EnableEurekaClient
@SpringBootApplication
@EntityScan("edu.cta.academy.common")
public class MsAlumnosApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MsAlumnosApplication.class, args);
	}

}
