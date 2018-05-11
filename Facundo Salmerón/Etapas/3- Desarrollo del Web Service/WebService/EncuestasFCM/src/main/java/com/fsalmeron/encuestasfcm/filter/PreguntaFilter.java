package com.fsalmeron.encuestasfcm.filter;

import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.model.Encuesta;

public class PreguntaFilter extends BaseFilter<Integer>{

	private Integer id;
	
	private String descripcion;
	
	private Encuesta encuesta;

	public Encuesta getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

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
	
	
}
