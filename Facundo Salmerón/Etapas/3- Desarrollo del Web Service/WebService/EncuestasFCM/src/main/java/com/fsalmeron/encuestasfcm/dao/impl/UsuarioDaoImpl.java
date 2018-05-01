package com.fsalmeron.encuestasfcm.dao.impl;

import org.springframework.stereotype.Repository;

import com.fsalmeron.encuestasfcm.base.BaseDaoImpl;
import com.fsalmeron.encuestasfcm.dao.UsuarioDao;
import com.fsalmeron.encuestasfcm.model.Usuario;

@Repository
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Integer> implements UsuarioDao{

}