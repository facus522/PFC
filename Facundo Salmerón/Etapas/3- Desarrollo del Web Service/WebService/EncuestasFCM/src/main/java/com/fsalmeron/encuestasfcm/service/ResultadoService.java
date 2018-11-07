package com.fsalmeron.encuestasfcm.service;

import java.util.List;

import org.json.JSONObject;

import com.fsalmeron.encuestasfcm.base.BaseService;
import com.fsalmeron.encuestasfcm.model.Resultado;

public interface ResultadoService extends BaseService<Resultado, Integer>{

	JSONObject save(Resultado resultado);
	
	public Integer getResultadosByUsuario(Integer idUsuario, Integer idEncuesta);
	
	public List<Resultado> getResultadosEncuesta(Integer idEncuesta);

}
