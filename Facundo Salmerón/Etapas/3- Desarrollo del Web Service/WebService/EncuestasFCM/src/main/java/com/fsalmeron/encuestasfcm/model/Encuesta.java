package com.fsalmeron.encuestasfcm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "ENCUESTA")
@Table(name = "ENCUESTA")
public class Encuesta extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{

	private String titulo;
	
	private String descripcion;
	
	private Boolean activo;
	
	private Integer idUsuarioAlta;
	
	private Date fechaAlta;
	
	private Integer idUsuarioModificacion;
	
	private Date fechaModificacion;
	
	private Integer idUsuarioEliminacion;
	
	private Date fechaEliminacion;

	private Integer resoluciones;
	
	private Boolean isGeolicalizada;
	
	@Column(name = "ISGEOLOCALIZADA")
	public Boolean getIsGeolicalizada() {
		return isGeolicalizada;
	}

	public void setIsGeolicalizada(Boolean isGeolicalizada) {
		this.isGeolicalizada = isGeolicalizada;
	}

	private List<Pregunta> preguntas = new ArrayList<Pregunta>();
	
	public Encuesta() {
		
	}
	
	public Encuesta(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "IDENCUESTA", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Encuesta_Generator")
	@SequenceGenerator(name = "Encuesta_Generator", sequenceName = "Encuesta_Generator")
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

	@Column(name = "ACTIVO")
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

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

	@Column(name = "IDUSUARIOMODIFICACION")
	public Integer getIdUsuarioModificacion() {
		return idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(Integer idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	@Column(name = "FECHAMODIFICACION")
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	@Column(name = "IDUSUARIOBAJA")
	public Integer getIdUsuarioEliminacion() {
		return idUsuarioEliminacion;
	}

	public void setIdUsuarioEliminacion(Integer idUsuarioEliminacion) {
		this.idUsuarioEliminacion = idUsuarioEliminacion;
	}

	@Column(name = "FECHABAJA")
	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "encuesta")
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	@Column(name = "RESOLUCIONES")
	public Integer getResoluciones() {
		return resoluciones;
	}

	public void setResoluciones(Integer resoluciones) {
		this.resoluciones = resoluciones;
	}
	
}
