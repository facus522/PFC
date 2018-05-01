package com.fsalmeron.encuestasfcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fsalmeron.encuestasfcm.base.BaseServiceImpl;
import com.fsalmeron.encuestasfcm.dao.UsuarioDao;
import com.fsalmeron.encuestasfcm.model.Usuario;
import com.fsalmeron.encuestasfcm.service.UsuarioService;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Integer> implements UsuarioService{

	@Autowired
	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}
	
}