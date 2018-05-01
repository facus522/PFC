package com.fsalmeron.encuestasfcm.model;

import java.util.Date;

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
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario extends com.fsalmeron.encuestasfcm.base.Entity<Integer>{

	private String nombreUsuario;
	
	private String password;
	
	private Date fechaNacimiento;
	
	private String mail;
	
	private Integer activo;
	
	private Sexo sexo;
	
	private TipoUsuario tipoUsuario;
	
	public Usuario() {
		
	}
	
	public Usuario(Integer id) {
		this.id = id;
	}
	
	@Override
	@Id
	@Column(name = "IDUSUARIO", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Usuario_Generator")
	@SequenceGenerator(name = "Usuario_Generator", sequenceName = "Usuario_Generator")
	public Integer getId() {
		return id;
	}

	@Column(name = "NOMBREUSUARIO")
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "FECHANACIMIENTO")
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Column(name = "MAIL")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "ACTIVO")
	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDSEXO")
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDTIPOUSUARIO")
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
}
