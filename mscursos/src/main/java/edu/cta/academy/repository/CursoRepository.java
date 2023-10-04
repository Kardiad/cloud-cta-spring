package edu.cta.academy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.cta.academy.common.entity.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long>{
	//ADD Query nativa que dado un id de alumno pues te saque el curso al que está asignado
	@Query(value = "select * from curso where id = "
			+ "(select curso_id from curso_alumnos where alumnos_id = ?1)", nativeQuery = true)
	public Optional<Curso> obtenerCursoPorAlumno(Long id);
}
