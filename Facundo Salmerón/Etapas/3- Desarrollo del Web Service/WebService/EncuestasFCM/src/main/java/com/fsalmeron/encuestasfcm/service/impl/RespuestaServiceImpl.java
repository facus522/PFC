package com.fsalmeron.encuestasfcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.RespuestaDao;
import com.fsalmeron.encuestasfcm.model.Respuesta;
import com.fsalmeron.encuestasfcm.service.RespuestaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RespuestaServiceImpl extends BaseServiceImpl<Respuesta, Integer> implements RespuestaService{

	@Autowired
	public void setDao(RespuestaDao dao) {
		this.dao = dao;
	}
	
}
