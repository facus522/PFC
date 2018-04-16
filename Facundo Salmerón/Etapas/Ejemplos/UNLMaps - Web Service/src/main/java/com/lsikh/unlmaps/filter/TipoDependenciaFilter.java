package com.lsikh.unlmaps.filter;

import com.lsikh.unlmaps.base.BaseFilter;

public class TipoDependenciaFilter extends BaseFilter<Integer>{
	
	private Integer id;
	
	private Boolean esVisible;
	
	private String descripcion;

	public Boolean getEsVisible() {
		return esVisible;
	}

	public void setEsVisible(Boolean esVisible) {
		this.esVisible = esVisible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
