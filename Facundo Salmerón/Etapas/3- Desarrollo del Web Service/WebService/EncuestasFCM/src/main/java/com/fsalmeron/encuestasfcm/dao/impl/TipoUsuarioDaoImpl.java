package com.fsalmeron.encuestasfcm.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fsalmeron.encuestasfcm.base.BaseDaoImpl;
import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.dao.TipoUsuarioDao;
import com.fsalmeron.encuestasfcm.filter.TipoUsuarioFilter;
import com.fsalmeron.encuestasfcm.model.TipoUsuario;

@Repository
public class TipoUsuarioDaoImpl extends BaseDaoImpl<TipoUsuario, Integer> implements TipoUsuarioDao{
	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		TipoUsuarioFilter filter = (TipoUsuarioFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (filter.getId() != null) {
			criteria.add(Restrictions.eq("id", filter.getId()));
		}
		
		if (!StringUtils.isEmpty(filter.getNombreTipoUsuario())) {
			criteria.add(Restrictions.eq("nombreTipoUsuario", filter.getNombreTipoUsuario()));
		}
		
		return criteria;
	}
}
