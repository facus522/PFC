package com.fsalmeron.encuestasfcm.service;

import org.json.JSONObject;

import com.fsalmeron.encuestasfcm.base.BaseService;
import com.fsalmeron.encuestasfcm.model.Pregunta;

public interface PreguntaService extends BaseService<Pregunta, Integer>{
	public JSONObject save(Pregunta pregunta, Integer idUsuario);

	public JSONObject delete(Pregunta pregunta, Integer idUsuario);
}
