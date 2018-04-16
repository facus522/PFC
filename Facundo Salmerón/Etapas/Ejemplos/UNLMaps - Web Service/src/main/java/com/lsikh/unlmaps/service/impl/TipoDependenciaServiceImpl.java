package com.lsikh.unlmaps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lsikh.unlmaps.base.BaseServiceImpl;
import com.lsikh.unlmaps.dao.TipoDependenciaDao;
import com.lsikh.unlmaps.model.TipoDependencia;
import com.lsikh.unlmaps.service.TipoDependenciaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TipoDependenciaServiceImpl extends BaseServiceImpl<TipoDependencia, Integer> implements TipoDependenciaService{

	@Autowired
	public void setDao(TipoDependenciaDao dao) {
		this.dao = dao;
	}
}
