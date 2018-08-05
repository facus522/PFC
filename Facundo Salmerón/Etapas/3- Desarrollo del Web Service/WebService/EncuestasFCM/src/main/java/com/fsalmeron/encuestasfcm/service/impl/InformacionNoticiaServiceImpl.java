package com.fsalmeron.encuestasfcm.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.InformacionNoticiaDao;
import com.fsalmeron.encuestasfcm.model.InformacionNoticia;
import com.fsalmeron.encuestasfcm.service.InformacionNoticiaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InformacionNoticiaServiceImpl extends BaseServiceImpl<InformacionNoticia, Integer> implements InformacionNoticiaService{

	@Autowired
	public void setDao(InformacionNoticiaDao dao) {
		this.dao = dao;
	}

	@Override
	public JSONObject save(InformacionNoticia informacionNoticia) {
		JSONObject resultado = new JSONObject();
		try {
			saveOrUpdate(informacionNoticia);
			resultado.put("exito", Boolean.TRUE);
		} catch (Exception e) {
			resultado.put("exito", Boolean.FALSE);
			resultado.put("error", e.getMessage());
		}
		return resultado;
	}
}
