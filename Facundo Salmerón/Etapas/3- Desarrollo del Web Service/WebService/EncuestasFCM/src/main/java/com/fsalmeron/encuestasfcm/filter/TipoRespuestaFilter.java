package com.fsalmeron.encuestasfcm.filter;

import com.fsalmeron.encuestasfcm.base.BaseFilter;

public class TipoRespuestaFilter extends BaseFilter<Integer>{
	private Integer id;
	
	private String nombreTipoRespuesta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreTipoRespuesta() {
		return nombreTipoRespuesta;
	}

	public void setNombreTipoRespuesta(String nombreTipoRespuesta) {
		this.nombreTipoRespuesta = nombreTipoRespuesta;
	}
	
	
}
