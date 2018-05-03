package com.fsalmeron.encuestasfcm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "TIPO_RESPUESTA")
@Table(name = "TIPO_RESPUESTA")
public class TipoRespuesta extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{

	private String nombreTipoRespuesta;
	
	public TipoRespuesta() {
		
	}	
	
	public TipoRespuesta(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "IDTIPORESPUESTA", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Tipo_Respuesta_Generator")
	@SequenceGenerator(name = "Tipo_Respuesta_Generator", sequenceName = "Tipo_Respuesta_Generator")
	public Integer getId() {
		return id;
	}
	
	@Column(name = "NOMBRE")
	public String getNombreTipoRespuesta() {
		return nombreTipoRespuesta;
	}

	public void setNombreTipoRespuesta(String nombreTipoRespuesta) {
		this.nombreTipoRespuesta = nombreTipoRespuesta;
	}
	
}
