package com.fsalmeron.encuestasfcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.ResultadoDao;
import com.fsalmeron.encuestasfcm.model.Resultado;
import com.fsalmeron.encuestasfcm.service.ResultadoService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ResultadoServiceImpl extends BaseServiceImpl<Resultado, Integer> implements ResultadoService{

	@Autowired
	public void setDao(ResultadoDao dao) {
		this.dao = dao;
	}
	
}
