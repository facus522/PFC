package com.fsalmeron.encuestasfcm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "INFORMACION_NOTICIA")
@Table(name = "INFORMACION_NOTICIA")
public class InformacionNoticia extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{
	
	private String titulo;
	
	private String descripcion;
	
	private String url;

	private Integer idUsuarioAlta;
	
	@Column(name = "IDUSUARIOALTA")
	public Integer getIdUsuarioAlta() {
		return idUsuarioAlta;
	}

	public void setIdUsuarioAlta(Integer idUsuarioAlta) {
		this.idUsuarioAlta = idUsuarioAlta;
	}

	@Column(name = "FECHAALTA")
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	private Date fechaAlta;
	
	public InformacionNoticia() {
		
	}
	
	public InformacionNoticia(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "InformacionNoticia_Generator")
	@SequenceGenerator(name = "InformacionNoticia_Generator", sequenceName = "InformacionNoticia_Generator")
	public Integer getId() {
		return id;
	}

	@Column(name = "TITULO")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
