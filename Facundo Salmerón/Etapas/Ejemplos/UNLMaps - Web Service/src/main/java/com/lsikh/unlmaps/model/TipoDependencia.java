package com.lsikh.unlmaps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "Tipo_Dependencia")
@Table(name = "Tipo_Dependencia")
public class TipoDependencia extends com.lsikh.unlmaps.base.Entity<Integer>{
	
	private String descripcion;
	
	private Boolean esVisible;

	public TipoDependencia() {
		
	}
	
	public TipoDependencia(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "idTipoDependencia", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Tipo_Dependencia_Generator")
	@SequenceGenerator(name = "Tipo_Dependencia_Generator", sequenceName = "Tipo_Dependencia_Generator")
	public Integer getId() {
		return id;
	}


	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "esVisible")
	public Boolean getEsVisible() {
		return esVisible;
	}

	public void setEsVisible(Boolean esVisible) {
		this.esVisible = esVisible;
	}
	
}
