package edu.cta.academy.service;

import java.util.List;
import java.util.Optional;

import edu.cta.academy.common.entity.Alumno;
import edu.cta.academy.common.entity.Curso;


public interface CursoService {
	public Iterable<Curso> listaCurso();
	public Optional<Curso> obtenerCursoPorId(Long id);
	public Optional<Curso> editarCurso(Curso curso, Long id);
	public Curso insertarCurso(Curso curso);
	public void borrarCurso(Long id);
	public Optional<Curso> anadirAlumnoCurso(List<Alumno> a, long id);
	public Optional<Curso> eliminarAlumno(Alumno a, long id);
	public Optional<Curso> obtenerCursoPorAlumno(Long id);
}
