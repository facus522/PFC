package com.fsalmeron.encuestasfcm.filter;

import com.fsalmeron.encuestasfcm.base.BaseFilter;

public class TipoRespuestaFilter extends BaseFilter<Integer>{
	private Long id;
	
	private String nombreTipoRespuesta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreTipoRespuesta() {
		return nombreTipoRespuesta;
	}

	public void setNombreTipoRespuesta(String nombreTipoRespuesta) {
		this.nombreTipoRespuesta = nombreTipoRespuesta;
	}
	
	
}
