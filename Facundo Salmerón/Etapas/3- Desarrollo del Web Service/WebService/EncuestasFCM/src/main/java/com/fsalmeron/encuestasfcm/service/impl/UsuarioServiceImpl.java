package com.fsalmeron.encuestasfcm.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.UsuarioDao;
import com.fsalmeron.encuestasfcm.filter.UsuarioFilter;
import com.fsalmeron.encuestasfcm.model.Usuario;
import com.fsalmeron.encuestasfcm.service.UsuarioService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Integer> implements UsuarioService{

	@Autowired
	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}

	@Override
	public JSONObject save(Usuario usuario) {
		JSONObject resultado = new JSONObject();
		if (validarUsuarioNuevo(usuario, resultado)) {
			resultado.put("exito", Boolean.TRUE);
			saveOrUpdate(usuario);
		}
		return resultado;
	}
	
	private Boolean validarUsuarioNuevo(Usuario usuario, JSONObject errors) {
		UsuarioFilter usuarioFilter = new UsuarioFilter();
		usuarioFilter.setActivo(Boolean.TRUE);
		usuarioFilter.setNombreUsuario(usuario.getNombreUsuario());
		
		if (!CollectionUtils.isEmpty(filter(usuarioFilter))) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "El nombre de usuario escogido ya se encuentra en uso.");
			return Boolean.FALSE;
		}
		
		if (usuario.getNombreUsuario().length() < 6) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "El nombre de usuario debe tener al menos 6 caracteres.");
			return Boolean.FALSE;
		}
		
		if (usuario.getPassword().length() < 6) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "La contraseña debe tener al menos 6 caracteres.");
			return Boolean.FALSE;
		}
		
		usuarioFilter.setNombreUsuario(null);
		usuarioFilter.setMail(usuario.getMail());
		if (!CollectionUtils.isEmpty(filter(usuarioFilter))) {
			errors.put("exito", Boolean.FALSE);
			errors.put("error", "El mail ingresado ya ha registrado una cuenta de usuario.");
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
}