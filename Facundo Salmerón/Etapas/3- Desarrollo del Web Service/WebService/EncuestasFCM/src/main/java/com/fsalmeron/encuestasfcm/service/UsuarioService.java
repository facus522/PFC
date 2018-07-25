package com.fsalmeron.encuestasfcm.service;


import org.json.JSONObject;

import com.fsalmeron.encuestasfcm.base.BaseService;
import com.fsalmeron.encuestasfcm.model.Usuario;

public interface UsuarioService extends BaseService<Usuario, Integer>{
	public JSONObject save(Usuario usuario, String validar);
}
