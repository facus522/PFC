package com.lsikh.unlmaps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lsikh.unlmaps.base.BaseServiceImpl;
import com.lsikh.unlmaps.dao.LimitesDao;
import com.lsikh.unlmaps.model.Limites;
import com.lsikh.unlmaps.service.LimitesService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LimitesServiceImpl extends BaseServiceImpl<Limites, Integer> implements LimitesService{
	
	@Autowired
	public void setDao(LimitesDao dao) {
		this.dao = dao;
	}

}
