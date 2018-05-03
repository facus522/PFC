package com.fsalmeron.encuestasfcm.filter;

import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.model.EncuestaPregunta;
import com.fsalmeron.encuestasfcm.model.TipoRespuesta;

public class RespuestaFilter extends BaseFilter<Integer>{
	
	private Long id;
	
	private String descripcion;
	
	private EncuestaPregunta encuestaPregunta;
	
	private TipoRespuesta tipoRespuesta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EncuestaPregunta getEncuestaPregunta() {
		return encuestaPregunta;
	}

	public void setEncuestaPregunta(EncuestaPregunta encuestaPregunta) {
		this.encuestaPregunta = encuestaPregunta;
	}

	public TipoRespuesta getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(TipoRespuesta tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}
	
	
}
