package com.fsalmeron.encuestasfcm.filter;

import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.model.Respuesta;
import com.fsalmeron.encuestasfcm.model.Usuario;

public class ResultadoFilter extends BaseFilter<Integer>{
	private Long id;
	
	private Double latitud;
	
	private Double longitud;
	
	private Integer edadEncuestado;
	
	private Integer sexoEncuestado;
	
	private Usuario usuario;
	
	private Respuesta respuesta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Integer getEdadEncuestado() {
		return edadEncuestado;
	}

	public void setEdadEncuestado(Integer edadEncuestado) {
		this.edadEncuestado = edadEncuestado;
	}

	public Integer getSexoEncuestado() {
		return sexoEncuestado;
	}

	public void setSexoEncuestado(Integer sexoEncuestado) {
		this.sexoEncuestado = sexoEncuestado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}	
	
	
}
