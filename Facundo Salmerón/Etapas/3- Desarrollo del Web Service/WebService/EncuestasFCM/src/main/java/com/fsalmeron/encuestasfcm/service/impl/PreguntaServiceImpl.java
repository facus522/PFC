package com.fsalmeron.encuestasfcm.service.impl;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.PreguntaDao;
import com.fsalmeron.encuestasfcm.model.Pregunta;
import com.fsalmeron.encuestasfcm.service.EncuestaService;
import com.fsalmeron.encuestasfcm.service.PreguntaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PreguntaServiceImpl extends BaseServiceImpl<Pregunta, Integer> implements PreguntaService{

	@Autowired
	private EncuestaService encuestaService;
	
	@Autowired
	public void setDao(PreguntaDao dao) {
		this.dao = dao;
	}
	
	@Override
	public JSONObject save(Pregunta pregunta, Integer idUsuario) {
		JSONObject resultado = new JSONObject();
		try {
			saveOrUpdate(pregunta);
			pregunta.getEncuesta().setFechaModificacion(new Date());
			pregunta.getEncuesta().setIdUsuarioModificacion(idUsuario);
			encuestaService.saveOrUpdate(pregunta.getEncuesta());
			resultado.put("exito", Boolean.TRUE);
		} catch (Exception e) {
			resultado.put("exito", Boolean.FALSE);
			resultado.put("error", e.getMessage());
		}
		return resultado;
	}

	@Override
	public JSONObject delete(Pregunta pregunta, Integer idUsuario) {
		JSONObject resultado = new JSONObject();
		try {
			remove(pregunta);
			pregunta.getEncuesta().setFechaModificacion(new Date());
			pregunta.getEncuesta().setIdUsuarioModificacion(idUsuario);
			encuestaService.saveOrUpdate(pregunta.getEncuesta());
			resultado.put("exito", Boolean.TRUE);
		} catch (Exception e) {
			resultado.put("exito", Boolean.FALSE);
			resultado.put("error", e.getMessage());
		}
		return resultado;
	}
	
}

