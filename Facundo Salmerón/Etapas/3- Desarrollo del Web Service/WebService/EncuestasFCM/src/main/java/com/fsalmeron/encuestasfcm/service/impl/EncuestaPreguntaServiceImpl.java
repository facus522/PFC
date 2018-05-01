package com.fsalmeron.encuestasfcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.EncuestaPreguntaDao;
import com.fsalmeron.encuestasfcm.model.EncuestaPregunta;
import com.fsalmeron.encuestasfcm.service.EncuestaPreguntaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EncuestaPreguntaServiceImpl extends BaseServiceImpl<EncuestaPregunta, Integer> implements EncuestaPreguntaService{

	@Autowired
	public void setDao(EncuestaPreguntaDao dao) {
		this.dao = dao;
	}
	
}
