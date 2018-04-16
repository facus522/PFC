package com.lsikh.unlmaps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "Unidad_Academica")
@Table(name = "Unidad_Academica")
public class UnidadAcademica extends com.lsikh.unlmaps.base.Entity<Integer>{
	
	private String nombre;

	@Override
	@Id
	@Column(name = "idUnidadAcademica", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Unidad_Academica_Generator")
	@SequenceGenerator(name = "Unidad_Academica_Generator", sequenceName = "Unidad_Academica_Generator")
	public Integer getId() {
		return id;
	}
	
	public UnidadAcademica() {
		
	}
	
	public UnidadAcademica(Integer id) {
		this.id = id;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
