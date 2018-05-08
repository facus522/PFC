package com.fsalmeron.encuestasfcm.filter;

import java.util.Date;

import com.fsalmeron.encuestasfcm.base.BaseFilter;

public class EncuestaFilter extends BaseFilter<Integer>{
private String titulo;
	
	private String descripcion;
	
	private Integer activo;
	
	private Integer idUsuarioAlta;
	
	private Date fechaAlta;
	
	private Integer idUsuarioModificacion;
	
	private Date fechaModificacion;
	
	private Integer idUsuarioEliminacion;
	
	private Date fechaEliminacion;
	
	private Integer id;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public Integer getIdUsuarioAlta() {
		return idUsuarioAlta;
	}

	public void setIdUsuarioAlta(Integer idUsuarioAlta) {
		this.idUsuarioAlta = idUsuarioAlta;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Integer getIdUsuarioModificacion() {
		return idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(Integer idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getIdUsuarioEliminacion() {
		return idUsuarioEliminacion;
	}

	public void setIdUsuarioEliminacion(Integer idUsuarioEliminacion) {
		this.idUsuarioEliminacion = idUsuarioEliminacion;
	}

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
