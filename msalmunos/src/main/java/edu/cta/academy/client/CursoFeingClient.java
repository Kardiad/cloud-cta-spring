package edu.cta.academy.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.cta.academy.common.entity.Curso;

@FeignClient(name="mscursos") //aquí va el nombre del microservicio
public interface CursoFeingClient {
	
	@GetMapping("/cursos/obtener-curso-por-alumno/{id}")
	public Optional<Curso> obtenerCursoAlumno(@PathVariable Long id);
	
}
