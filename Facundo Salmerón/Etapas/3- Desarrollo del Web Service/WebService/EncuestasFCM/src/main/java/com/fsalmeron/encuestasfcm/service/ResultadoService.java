package com.fsalmeron.encuestasfcm.service;

import org.json.JSONObject;

import com.fsalmeron.encuestasfcm.base.BaseService;
import com.fsalmeron.encuestasfcm.model.Resultado;

public interface ResultadoService extends BaseService<Resultado, Integer>{

	JSONObject save(Resultado resultado);
	
	public Integer getResultadosByUsuario(Integer idUsuario, Integer idEncuesta);

}
