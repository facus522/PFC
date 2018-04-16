package com.lsikh.unlmaps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "Edificio")
@Table(name = "Edificio")
public class Edificio extends com.lsikh.unlmaps.base.Entity<Integer>{

	private Integer idInterno;
	
	private String nombre;
	
	private Integer cantidadPisos;
	
	private Limites limites;
	
	public Edificio() {
		
	}
	
	public Edificio(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "idEdificio", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Edificio_Generator")
	@SequenceGenerator(name = "Edificio_Generator", sequenceName = "Edificio_Generator")
	public Integer getId() {
		return id;
	}

	@Column(name = "idInternoEdificio")
	public Integer getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(Integer idInterno) {
		this.idInterno = idInterno;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "cantidadPisos")
	public Integer getCantidadPisos() {
		return cantidadPisos;
	}

	public void setCantidadPisos(Integer cantidadPisos) {
		this.cantidadPisos = cantidadPisos;
	}

	@OneToOne
	@JoinColumn(name = "idLimites")
	public Limites getLimites() {
		return limites;
	}

	public void setLimites(Limites limites) {
		this.limites = limites;
	}
}
