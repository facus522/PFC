package com.fsalmeron.encuestasfcm.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.UsuarioDao;
import com.fsalmeron.encuestasfcm.enun.TipoUsuarioEnum;
import com.fsalmeron.encuestasfcm.filter.UsuarioFilter;
import com.fsalmeron.encuestasfcm.model.Usuario;
import com.fsalmeron.encuestasfcm.service.UsuarioService;
import com.fsalmeron.encuestasfcm.utils.EncryptionUtil;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Integer> implements UsuarioService{

	@Autowired
	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}

	@Override
	public JSONObject save(Usuario usuario, String validar) {
		JSONObject resultado = new JSONObject();
		
		if (validarUsuarioNuevo(usuario, validar, resultado)) {
			try {
				saveOrUpdate(usuario);
				resultado.put("exito", Boolean.TRUE);
			} catch (Exception e) {
				resultado.put("error", e.getMessage());
			}
			
		}
		return resultado;
	}
	
	private Boolean validarUsuarioNuevo(Usuario usuario, String validar, JSONObject errors) {
		UsuarioFilter usuarioFilter = new UsuarioFilter();
		usuarioFilter.setActivo(Boolean.TRUE);
		usuarioFilter.setNombreUsuario(usuario.getNombreUsuario());

		if (usuario.getTipoUsuario().getId().equals(TipoUsuarioEnum.USUARIO_ESPECIAL.getCodigo()) && !validar.equals("FCM2018")) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "El código de validación ingresado como Usuario de Medicina es incorrecto!");
			return Boolean.FALSE;
		}
		
		if (!CollectionUtils.isEmpty(filter(usuarioFilter))) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "El Nombre de Usuario escogido ya se encuentra en uso.");
			return Boolean.FALSE;
		}
		
		if (usuario.getNombreUsuario().length() < 6) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "El Nombre de Usuario debe tener al menos 6 caracteres.");
			return Boolean.FALSE;
		}
		
		if (usuario.getNombreUsuario().length() > 100) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "El Nombre de Usuario debe tener como máximo 100 caracteres.");
			return Boolean.FALSE;
		}
		
		if (EncryptionUtil.decode(usuario.getPassword()).length() < 6) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "La Contraseña debe tener al menos 6 caracteres.");
			return Boolean.FALSE;
		}
		
		if (EncryptionUtil.decode(usuario.getPassword()).length() > 100) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "La Contraseña debe tener como máximo 100 caracteres.");
			return Boolean.FALSE;
		}
		
		if (!usuario.getNombreUsuario().matches("^[a-zA-Z0-9._-]{3,}$")) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "Existen caracteres inválidos en el Nombre de Usuario.");
			return Boolean.FALSE;
		}
		
		usuarioFilter.setNombreUsuario(null);
		usuarioFilter.setMail(usuario.getMail());
		if (!CollectionUtils.isEmpty(filter(usuarioFilter))) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "El Mail ingresado ya ha registrado una cuenta de usuario.");
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
}