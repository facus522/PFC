package com.fsalmeron.encuestasfcm.service;

import org.json.JSONObject;

import com.fsalmeron.encuestasfcm.base.BaseService;
import com.fsalmeron.encuestasfcm.model.Respuesta;

public interface RespuestaService extends BaseService<Respuesta, Integer>{

	JSONObject save(Respuesta respuesta, Integer idUsuario);

	JSONObject delete(Respuesta respuesta, Integer idUsuario);

}
