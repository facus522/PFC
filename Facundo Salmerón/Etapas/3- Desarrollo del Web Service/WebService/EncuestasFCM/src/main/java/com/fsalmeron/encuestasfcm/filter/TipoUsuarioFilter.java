package com.fsalmeron.encuestasfcm.filter;

import com.fsalmeron.encuestasfcm.base.BaseFilter;

public class TipoUsuarioFilter extends BaseFilter<Integer>{
	private Integer id;
	
	private String nombreTipoUsuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreTipoUsuario() {
		return nombreTipoUsuario;
	}

	public void setNombreTipoUsuario(String nombreTipoUsuario) {
		this.nombreTipoUsuario = nombreTipoUsuario;
	}
	
	
}
