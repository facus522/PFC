package com.lsikh.unlmaps.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity(name = "Punto")
@Table(name = "Punto")
public class Punto extends com.lsikh.unlmaps.base.Entity<Integer>  implements Comparable<Punto> {

	private Double latitud;

	private Double longitud;

	private Integer piso;

	private String nombre;

	private Boolean tieneImagen;

	private Edificio edificio;

	private TipoDependencia tipoDependencia;

	private UnidadAcademica unidadAcademica;

	private Set<Punto> vecinos = new HashSet<Punto>();

	private Punto padre;

	public Double costo = new Double(0);

	public Punto() {

	}

	public Punto(Integer id) {
		this.id = id;
	}

	@Override
	@Id
	@Column(name = "idPunto", nullable = false, unique = true)
	public Integer getId() {
		return id;
	}

	@Column(name = "latitud")
	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	@Column(name = "longitud")
	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	@Column(name = "piso")
	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "tieneImagen")
	public Boolean getTieneImagen() {
		return tieneImagen;
	}

	public void setTieneImagen(Boolean tieneImagen) {
		this.tieneImagen = tieneImagen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEdificio")
	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTipoDependencia")
	public TipoDependencia getTipoDependencia() {
		return tipoDependencia;
	}

	public void setTipoDependencia(TipoDependencia tipoDependencia) {
		this.tipoDependencia = tipoDependencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUnidadAcademica")
	public UnidadAcademica getUnidadAcademica() {
		return unidadAcademica;
	}

	public void setUnidadAcademica(UnidadAcademica unidadAcademica) {
		this.unidadAcademica = unidadAcademica;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Conexiones", joinColumns = { 
			@JoinColumn(name = "idPuntoA") }, inverseJoinColumns = { 
					@JoinColumn(name = "idPuntoB") })
	public Set<Punto> getVecinos() {
		return vecinos;
	}

	public void setVecinos(Set<Punto> vecinos) {
		this.vecinos = vecinos;
	}

	@Transient
	public Punto getPadre() {
		return padre;
	}

	public void setPadre(Punto padre) {
		this.padre = padre;
	}

	@Transient
	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	@Override
	@Transient
	public int compareTo(Punto o) {
		int c;
		if(this.equals(o)){
			c = 0;
		}
		else{
			if (this.costo > o.costo){
				c = 1;
			}
			else{
				c = -1;
			}
		}
		return c;
	}
	
	@Transient
	public Integer getCantidadVecinos() {
		return vecinos.size();
	}
}
