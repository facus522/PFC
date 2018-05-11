package com.fsalmeron.encuestasfcm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "PREGUNTA")
@Table(name = "PREGUNTA")
public class Pregunta extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{

	private String descripcion;
	
	private Encuesta encuesta;
	
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDENCUESTA")
	public Encuesta getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}
	
}
