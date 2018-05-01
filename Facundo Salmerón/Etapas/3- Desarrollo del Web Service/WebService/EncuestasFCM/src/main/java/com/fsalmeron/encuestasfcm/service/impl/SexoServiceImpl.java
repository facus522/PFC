package com.fsalmeron.encuestasfcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.SexoDao;
import com.fsalmeron.encuestasfcm.model.Sexo;
import com.fsalmeron.encuestasfcm.service.SexoService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SexoServiceImpl extends BaseServiceImpl<Sexo, Integer> implements SexoService{

	@Autowired
	public void setDao(SexoDao dao) {
		this.dao = dao;
	}
	
}