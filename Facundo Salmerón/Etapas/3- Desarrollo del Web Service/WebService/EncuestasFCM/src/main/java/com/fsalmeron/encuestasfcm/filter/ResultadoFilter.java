package com.fsalmeron.encuestasfcm.filter;

import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.model.Respuesta;
import com.fsalmeron.encuestasfcm.model.Usuario;

public class ResultadoFilter extends BaseFilter<Integer>{
	private Integer id;
	
	private String latitud;
	
	private String longitud;
	
	private Integer edadEncuestado;
	
	private Integer sexoEncuestado;
	
	private Usuario usuario;
	
	private Respuesta respuesta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
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
