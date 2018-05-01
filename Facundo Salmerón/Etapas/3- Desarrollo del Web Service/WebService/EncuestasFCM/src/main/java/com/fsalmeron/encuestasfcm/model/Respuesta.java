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
@Entity(name = "respuesta")
@Table(name = "respuesta")
public class Respuesta extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{
	
	private String descripcion;
	
	private EncuestaPregunta encuestaPregunta;
	
	private TipoRespuesta tipoRespuesta;
	
	public Respuesta() {
		
	}
	
	public Respuesta(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "IDRESPUESTA", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Respuesta_Generator")
	@SequenceGenerator(name = "Respuesta_Generator", sequenceName = "Respuesta_Generator")
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
	@JoinColumn(name = "IDENCUESTAPREGUNTA")
	public EncuestaPregunta getEncuestaPregunta() {
		return encuestaPregunta;
	}

	public void setEncuestaPregunta(EncuestaPregunta encuestaPregunta) {
		this.encuestaPregunta = encuestaPregunta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDTIPORESPUESTA")
	public TipoRespuesta getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(TipoRespuesta tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}
	
	
}
