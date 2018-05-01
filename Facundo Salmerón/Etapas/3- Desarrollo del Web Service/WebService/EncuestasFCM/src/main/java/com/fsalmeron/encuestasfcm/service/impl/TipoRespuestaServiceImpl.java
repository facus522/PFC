package com.fsalmeron.encuestasfcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.TipoRespuestaDao;
import com.fsalmeron.encuestasfcm.model.TipoRespuesta;
import com.fsalmeron.encuestasfcm.service.TipoRespuestaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TipoRespuestaServiceImpl extends BaseServiceImpl<TipoRespuesta, Integer> implements TipoRespuestaService{

	@Autowired
	public void setDao(TipoRespuestaDao dao) {
		this.dao = dao;
	}
	
}
