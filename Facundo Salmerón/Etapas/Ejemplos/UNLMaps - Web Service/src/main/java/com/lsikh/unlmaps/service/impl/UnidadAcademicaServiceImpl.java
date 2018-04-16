package com.lsikh.unlmaps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lsikh.unlmaps.base.BaseServiceImpl;
import com.lsikh.unlmaps.dao.UnidadAcademicaDao;
import com.lsikh.unlmaps.model.UnidadAcademica;
import com.lsikh.unlmaps.service.UnidadAcademicaService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UnidadAcademicaServiceImpl extends BaseServiceImpl<UnidadAcademica, Integer> implements UnidadAcademicaService{
	
	@Autowired
	public void setDao(UnidadAcademicaDao dao) {
		this.dao = dao;
	}

}
