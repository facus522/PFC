package com.fsalmeron.encuestasfcm.service.impl;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.RespuestaDao;
import com.fsalmeron.encuestasfcm.model.Respuesta;
import com.fsalmeron.encuestasfcm.service.EncuestaService;
import com.fsalmeron.encuestasfcm.service.RespuestaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RespuestaServiceImpl extends BaseServiceImpl<Respuesta, Integer> implements RespuestaService{

	@Autowired
	private EncuestaService encuestaService;
	
	@Autowired
	public void setDao(RespuestaDao dao) {
		this.dao = dao;
	}

	@Override
	public JSONObject save(Respuesta respuesta, Integer idUsuario) {
		JSONObject resultado = new JSONObject();
		try {
			saveOrUpdate(respuesta);
			respuesta.getPregunta().getEncuesta().setFechaModificacion(new Date());
			respuesta.getPregunta().getEncuesta().setIdUsuarioModificacion(idUsuario);
			encuestaService.saveOrUpdate(respuesta.getPregunta().getEncuesta());
			resultado.put("exito", Boolean.TRUE);
			resultado.put("idAsignado", respuesta.getId());
		} catch (Exception e) {
			resultado.put("exito", Boolean.FALSE);
			resultado.put("error", e.getMessage());
		}
		return resultado;
	}
	
	@Override
	public JSONObject delete(Respuesta respuesta, Integer idUsuario) {
		JSONObject resultado = new JSONObject();
		try {
			remove(respuesta);
			respuesta.getPregunta().getEncuesta().setFechaModificacion(new Date());
			respuesta.getPregunta().getEncuesta().setIdUsuarioModificacion(idUsuario);
			encuestaService.saveOrUpdate(respuesta.getPregunta().getEncuesta());
			resultado.put("exito", Boolean.TRUE);
		} catch (Exception e) {
			resultado.put("exito", Boolean.FALSE);
			resultado.put("error", e.getMessage());
		}
		return resultado;
	}
	
}
