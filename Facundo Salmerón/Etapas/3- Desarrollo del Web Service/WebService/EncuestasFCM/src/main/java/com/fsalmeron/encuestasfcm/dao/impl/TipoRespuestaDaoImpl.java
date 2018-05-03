package com.fsalmeron.encuestasfcm.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fsalmeron.encuestasfcm.base.BaseDaoImpl;
import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.dao.TipoRespuestaDao;
import com.fsalmeron.encuestasfcm.filter.TipoRespuestaFilter;
import com.fsalmeron.encuestasfcm.model.TipoRespuesta;

@Repository
public class TipoRespuestaDaoImpl extends BaseDaoImpl<TipoRespuesta, Integer> implements TipoRespuestaDao{
	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		TipoRespuestaFilter filter = (TipoRespuestaFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (filter.getId() != null) {
			criteria.add(Restrictions.eq("id", filter.getId()));
		}
		
		if (!StringUtils.isEmpty(filter.getNombreTipoRespuesta())) {
			criteria.add(Restrictions.eq("nombreTipoRespuesta", filter.getNombreTipoRespuesta()));
		}
		
		return criteria;
	}
	
}