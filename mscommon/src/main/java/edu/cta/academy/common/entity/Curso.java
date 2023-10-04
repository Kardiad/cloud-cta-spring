package edu.cta.academy.common.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter  
	@Setter
	private Long id;
	
	@Column(name="nombre")
	@Getter 
	@Setter
	private String nombre;

	@OneToMany(fetch=FetchType.LAZY)
	@Getter
	@Setter
	//@JsonIgnorne
	List<Alumno> alumnos;
	
	public void addAlumno(Alumno a) {
		this.alumnos.add(a);
	}
	
	public void eliminarAlumno(Alumno a) {
		this.alumnos.remove(a);
	}
	
}
