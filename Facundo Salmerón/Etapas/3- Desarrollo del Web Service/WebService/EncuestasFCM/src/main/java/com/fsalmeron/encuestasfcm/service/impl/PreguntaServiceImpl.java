package com.fsalmeron.encuestasfcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.PreguntaDao;
import com.fsalmeron.encuestasfcm.model.Pregunta;
import com.fsalmeron.encuestasfcm.service.PreguntaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PreguntaServiceImpl extends BaseServiceImpl<Pregunta, Integer> implements PreguntaService{

	@Autowired
	public void setDao(PreguntaDao dao) {
		this.dao = dao;
	}
	
}

