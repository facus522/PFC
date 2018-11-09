package com.fsalmeron.encuestasfcm.service.impl;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.EncuestaDao;
import com.fsalmeron.encuestasfcm.model.Encuesta;
import com.fsalmeron.encuestasfcm.model.Pregunta;
import com.fsalmeron.encuestasfcm.model.Respuesta;
import com.fsalmeron.encuestasfcm.service.EncuestaService;
import com.fsalmeron.encuestasfcm.service.PreguntaService;
import com.fsalmeron.encuestasfcm.service.RespuestaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EncuestaServiceImpl extends BaseServiceImpl<Encuesta, Integer> implements EncuestaService{

	@Autowired
	private RespuestaService respuestaService;
	
	@Autowired
	private PreguntaService preguntaService;
	
	@Autowired
	public void setDao(EncuestaDao dao) {
		this.dao = dao;
	}
	
	@Override
	public JSONObject save(Encuesta encuesta, Integer idUsuario) {
		JSONObject resultado = new JSONObject();
		try {
			if (encuesta.getId() != null){
				encuesta.setFechaModificacion(new Date());
				encuesta.setIdUsuarioModificacion(idUsuario);
			}
			saveOrUpdate(encuesta);
			resultado.put("exito", Boolean.TRUE);
			resultado.put("idAsignado", encuesta.getId());
		} catch (Exception e) {
			resultado.put("exito", Boolean.FALSE);
			resultado.put("error", e.getMessage());
		}
		return resultado;
	}

	@Override
	public JSONObject bajaSoftware(Encuesta encuesta, Integer idUsuario) {
		JSONObject resultado = new JSONObject();
		encuesta.setActivo(Boolean.FALSE);
		encuesta.setHabilitada(Boolean.FALSE);
		encuesta.setIdUsuarioEliminacion(idUsuario);
		encuesta.setFechaEliminacion(new Date());
		try {
			saveOrUpdate(encuesta);
			resultado.put("exito", Boolean.TRUE);
		} catch (Exception e) {
			resultado.put("exito", Boolean.FALSE);
			resultado.put("error", e.getMessage());
		}
		return resultado;
	}
	
	@Override
	public JSONObject inhabilitar(Encuesta encuesta, Integer idUsuario) {
		JSONObject resultado = new JSONObject();
		encuesta.setHabilitada(Boolean.FALSE);
		encuesta.setIdUsuarioModificacion(idUsuario);
		encuesta.setFechaModificacion(new Date());
		try {
			saveOrUpdate(encuesta);
			resultado.put("exito", Boolean.TRUE);
		} catch (Exception e) {
			resultado.put("exito", Boolean.FALSE);
			resultado.put("error", e.getMessage());
		}
		return resultado;
	}
	
	@Override
	public JSONObject habilitar(Encuesta encuesta, Integer idUsuario) {
		JSONObject resultado = new JSONObject();
		encuesta.setHabilitada(Boolean.TRUE);
		encuesta.setIdUsuarioModificacion(idUsuario);
		encuesta.setFechaModificacion(new Date());
		try {
			saveOrUpdate(encuesta);
			resultado.put("exito", Boolean.TRUE);
		} catch (Exception e) {
			resultado.put("exito", Boolean.FALSE);
			resultado.put("error", e.getMessage());
		}
		return resultado;
	}
	
	@Override
	public JSONObject delete(Encuesta encuesta) {
		JSONObject resultado = new JSONObject();
		try {
			for (Pregunta pregunta : encuesta.getPreguntas()) {
				for (Respuesta respuesta : pregunta.getRespuestas()) {
					respuestaService.remove(respuesta);
				}
				preguntaService.remove(pregunta);
			}
			remove(encuesta);
			resultado.put("exito", Boolean.TRUE);
		} catch (Exception e) {
			resultado.put("exito", Boolean.FALSE);
			resultado.put("error", e.getMessage());
		}			
			
		return resultado;
	}

}
