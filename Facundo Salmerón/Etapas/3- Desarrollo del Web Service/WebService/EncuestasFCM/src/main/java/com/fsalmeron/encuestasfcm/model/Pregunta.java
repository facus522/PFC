package com.fsalmeron.encuestasfcm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "pregunta")
@Table(name = "pregunta")
public class Pregunta extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{

	private String descripcion;
	
	public Pregunta() {
		
	}
	
	public Pregunta(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "IDPREGUNTA", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Pregunta_Generator")
	@SequenceGenerator(name = "Pregunta_Generator", sequenceName = "Pregunta_Generator")
	public Integer getId() {
		return id;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
