package edu.cta.academy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.cta.academy.common.entity.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long>{
	
}
