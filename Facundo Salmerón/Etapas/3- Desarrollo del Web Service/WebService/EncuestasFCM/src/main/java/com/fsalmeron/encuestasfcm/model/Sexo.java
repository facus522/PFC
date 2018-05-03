package com.fsalmeron.encuestasfcm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity(name = "SEXO")
@Table(name = "SEXO")
public class Sexo extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{

	private String descripcion;
	
	public Sexo() {
		
	}

	public Sexo(Integer id) {
		this.id = id;
	}

	@Override
	@Id
	@Column(name = "IDSEXO", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Sexo_Generator")
	@SequenceGenerator(name = "Sexo_Generator", sequenceName = "Sexo_Generator")
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


}
