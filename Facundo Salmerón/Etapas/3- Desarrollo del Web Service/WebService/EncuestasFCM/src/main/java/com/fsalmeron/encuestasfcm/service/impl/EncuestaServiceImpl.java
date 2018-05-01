package com.fsalmeron.encuestasfcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.EncuestaDao;
import com.fsalmeron.encuestasfcm.model.Encuesta;
import com.fsalmeron.encuestasfcm.service.EncuestaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EncuestaServiceImpl extends BaseServiceImpl<Encuesta, Integer> implements EncuestaService{

	@Autowired
	public void setDao(EncuestaDao dao) {
		this.dao = dao;
	}
	
}
