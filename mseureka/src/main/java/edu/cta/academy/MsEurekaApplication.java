package edu.cta.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsEurekaApplication {
	
	/*
	 * 1º Paso creamos con spring starter y dependencia eureka
	 * 2º Añado dependencia jaxb
	 * 3º Anotacion @EnableEurekaServer
	 * 4º Configuración del properties:
	 * 			spring.application.name=EurekaServer
	 *			server.port=8761
	 *			eureka.client.register-with-eureka=false
	 *			eureka.client.fetch-registry=false
	 * */

	public static void main(String[] args) {
		SpringApplication.run(MsEurekaApplication.class, args);
	}

}
