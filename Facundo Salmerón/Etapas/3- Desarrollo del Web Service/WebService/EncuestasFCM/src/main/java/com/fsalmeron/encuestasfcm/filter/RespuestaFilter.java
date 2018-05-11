package com.fsalmeron.encuestasfcm.filter;

import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.model.Pregunta;
import com.fsalmeron.encuestasfcm.model.TipoRespuesta;

public class RespuestaFilter extends BaseFilter<Integer>{
	
	private Integer id;
	
	private String descripcion;
	
	private Pregunta pregunta;
	
	private TipoRespuesta tipoRespuesta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public TipoRespuesta getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(TipoRespuesta tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}
	
	
}
