package com.fsalmeron.encuestasfcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.TipoUsuarioDao;
import com.fsalmeron.encuestasfcm.model.TipoUsuario;
import com.fsalmeron.encuestasfcm.service.TipoUsuarioService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TipoUsuarioServiceImpl extends BaseServiceImpl<TipoUsuario, Integer> implements TipoUsuarioService{

	@Autowired
	public void setDao(TipoUsuarioDao dao) {
		this.dao = dao;
	}
	
}
