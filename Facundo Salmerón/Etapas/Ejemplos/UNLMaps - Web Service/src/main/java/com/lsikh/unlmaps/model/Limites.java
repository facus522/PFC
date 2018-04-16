package com.lsikh.unlmaps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity(name = "Limites")
@Table(name = "Limites")
public class Limites extends com.lsikh.unlmaps.base.Entity<Integer>{
	
	private String nombre;
	
	private Double latitudEsqInfIzq;
	
	private Double longitudEsqInfIzq;
	
	private Double latitudEsqSupDer;
	
	private Double longitudEsqSupDer;
	
	public Limites() {
		
	}
	
	public Limites(Integer id) {
		this.id = id;
	}

	@Override
	@Id
	@Column(name = "idLimite", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Limites_Generator")
	@SequenceGenerator(name = "Limites_Generator", sequenceName = "Limites_Generator")
	public Integer getId() {
		return id;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "latitudEsqInfIzq")
	public Double getLatitudEsqInfIzq() {
		return latitudEsqInfIzq;
	}

	public void setLatitudEsqInfIzq(Double latitudEsqInfIzq) {
		this.latitudEsqInfIzq = latitudEsqInfIzq;
	}

	@Column(name = "longitudEsqInfIzq")
	public Double getLongitudEsqInfIzq() {
		return longitudEsqInfIzq;
	}

	public void setLongitudEsqInfIzq(Double longitudEsqInfIzq) {
		this.longitudEsqInfIzq = longitudEsqInfIzq;
	}

	@Column(name = "latitudEsqSupDer")
	public Double getLatitudEsqSupDer() {
		return latitudEsqSupDer;
	}

	public void setLatitudEsqSupDer(Double latitudEsqSupDer) {
		this.latitudEsqSupDer = latitudEsqSupDer;
	}

	@Column(name = "longitudEsqSupDer")
	public Double getLongitudEsqSupDer() {
		return longitudEsqSupDer;
	}

	public void setLongitudEsqSupDer(Double longitudEsqSupDer) {
		this.longitudEsqSupDer = longitudEsqSupDer;
	}

}
