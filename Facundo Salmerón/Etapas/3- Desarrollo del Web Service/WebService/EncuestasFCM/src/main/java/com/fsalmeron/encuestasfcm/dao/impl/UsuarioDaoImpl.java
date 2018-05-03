package com.fsalmeron.encuestasfcm.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fsalmeron.encuestasfcm.base.BaseDaoImpl;
import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.dao.UsuarioDao;
import com.fsalmeron.encuestasfcm.filter.UsuarioFilter;
import com.fsalmeron.encuestasfcm.model.Usuario;

@Repository
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Integer> implements UsuarioDao{

	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		UsuarioFilter filter = (UsuarioFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (filter.getId() != null) {
			criteria.add(Restrictions.eq("id", filter.getId()));
		}
		
		if (!StringUtils.isEmpty(filter.getNombreUsuario())) {
			criteria.add(Restrictions.eq("nombreUsuario", filter.getNombreUsuario()));
		}
		
		if (!StringUtils.isEmpty(filter.getPassword())) {
			criteria.add(Restrictions.eq("password", filter.getPassword()));
		}
		
		if (!StringUtils.isEmpty(filter.getMail())) {
			criteria.add(Restrictions.eq("mail", filter.getMail()));
		}
		
		if (filter.getFechaNacimiento() != null) {
			criteria.add(Restrictions.eq("fechaNacimiento", filter.getFechaNacimiento()));
		}
		
		if (filter.getActivo() != null) {
			criteria.add(Restrictions.eq("activo", filter.getActivo()));
		}
		
		if (filter.getSexo() != null) {
			criteria.add(Restrictions.eq("sexo", filter.getSexo()));
		}
		
		if (filter.getTipoUsuario() != null) {
			criteria.add(Restrictions.eq("tipoUsuario", filter.getTipoUsuario()));
		}
		return criteria;
	}
	
}