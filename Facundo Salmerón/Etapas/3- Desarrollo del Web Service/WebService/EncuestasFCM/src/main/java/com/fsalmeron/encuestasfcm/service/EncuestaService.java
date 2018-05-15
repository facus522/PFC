package com.fsalmeron.encuestasfcm.service;

import org.json.JSONObject;

import com.fsalmeron.encuestasfcm.base.BaseService;
import com.fsalmeron.encuestasfcm.model.Encuesta;

public interface EncuestaService extends BaseService<Encuesta, Integer>{

	JSONObject save(Encuesta encuesta, Integer idUsuario);

	JSONObject delete(Encuesta encuesta);

	JSONObject inhabilitar(Encuesta encuesta, Integer idUsuario);

	JSONObject habilitar(Encuesta encuesta, Integer idUsuario);

}
