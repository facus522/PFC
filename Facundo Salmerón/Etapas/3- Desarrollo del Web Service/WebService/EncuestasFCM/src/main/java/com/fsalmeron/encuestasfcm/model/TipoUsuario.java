package com.fsalmeron.encuestasfcm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "TIPO_USUARIO")
@Table(name = "TIPO_USUARIO")
public class TipoUsuario extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{

	private String nombreTipoUsuario;
	
	public TipoUsuario() {
		
	}
	
	public TipoUsuario(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "IDTIPOUSUARIO", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO,
			generator = "Tipo_Usuario_Generator")
	@SequenceGenerator(name = "Tipo_Usuario_Generator", sequenceName = "Tipo_Usuario_Generator")
	public Integer getId() {
		return id;
	}
	
	@Column(name = "NOMBRETIPOUSUARIO")
	public String getNombreTipoUsuario() {
		return nombreTipoUsuario;
	}

	public void setNombreTipoUsuario(String nombreTipoUsuario) {
		this.nombreTipoUsuario = nombreTipoUsuario;
	}

}
