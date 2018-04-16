package com.lsikh.unlmaps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lsikh.unlmaps.base.BaseServiceImpl;
import com.lsikh.unlmaps.dao.EdificioDao;
import com.lsikh.unlmaps.model.Edificio;
import com.lsikh.unlmaps.service.EdificioService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EdificioServiceImpl extends BaseServiceImpl<Edificio, Integer> implements EdificioService{

	@Autowired
	public void setDao(EdificioDao dao) {
		this.dao = dao;
	}
	
}
