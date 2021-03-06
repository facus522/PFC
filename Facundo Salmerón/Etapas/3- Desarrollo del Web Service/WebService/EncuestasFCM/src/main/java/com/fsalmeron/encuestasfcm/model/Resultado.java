package com.fsalmeron.encuestasfcm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "RESULTADO")
@Table(name = "RESULTADO")
public class Resultado extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{

	private String latitud;
	
	private String longitud;
	
	private Integer edadEncuestado;
	
	private Integer sexoEncuestado;
	
	private Usuario usuario;
	
	private Respuesta respuesta;
	
	private String descripcion;
	
	private Integer idEncuesta;
	
	public Resultado() {
		
	}
	
	public Resultado(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "IDRESULTADO", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Resultado_Generator")
	@SequenceGenerator(name = "Resultado_Generator", sequenceName = "Resultado_Generator")
	public Integer getId() {
		return id;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "LATITUD")
	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	@Column(name = "LONGITUD")
	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	@Column(name = "EDAD_ENCUESTADO")
	public Integer getEdadEncuestado() {
		return edadEncuestado;
	}

	public void setEdadEncuestado(Integer edadEncuestado) {
		this.edadEncuestado = edadEncuestado;
	}

	@Column(name = "SEXO_ENCUESTADO")
	public Integer getSexoEncuestado() {
		return sexoEncuestado;
	}

	public void setSexoEncuestado(Integer sexoEncuestado) {
		this.sexoEncuestado = sexoEncuestado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDUSUARIO")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDRESPUESTA")
	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@Column(name = "IDENCUESTA")
	public Integer getIdEncuesta() {
		return idEncuesta;
	}

	public void setIdEncuesta(Integer idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	
	
	
	
}
