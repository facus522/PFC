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
@Entity(name = "encuesta_pregunta")
@Table(name = "encuesta_pregunta")
public class EncuestaPregunta extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{

	private Encuesta encuesta;
	
	private Pregunta pregunta;
	
	public EncuestaPregunta() {
		
	}
	
	public EncuestaPregunta(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "IDENCUESTAPREGUNTA", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EncuestaPregunta_Generator")
	@SequenceGenerator(name = "EncuestaPregunta_Generator", sequenceName = "EncuestaPregunta_Generator")
	public Integer getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDENCUESTA")
	public Encuesta getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPREGUNTA")
	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	
}
