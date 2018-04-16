package com.lsikh.unlmaps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lsikh.unlmaps.base.BaseServiceImpl;
import com.lsikh.unlmaps.dao.PuntoDao;
import com.lsikh.unlmaps.model.Punto;
import com.lsikh.unlmaps.service.PuntoService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PuntoServiceImpl  extends BaseServiceImpl<Punto, Integer> implements PuntoService{

	@Autowired
	public void setDao(PuntoDao dao) {
		this.dao = dao;
	}
	
}
