package com.fsalmeron.encuestasfcm.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fsalmeron.encuestasfcm.base.BaseDaoImpl;
import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.dao.PreguntaDao;
import com.fsalmeron.encuestasfcm.filter.PreguntaFilter;
import com.fsalmeron.encuestasfcm.model.Pregunta;

@Repository
public class PreguntaDaoImpl extends BaseDaoImpl<Pregunta, Integer> implements PreguntaDao{

	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		PreguntaFilter filter = (PreguntaFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (filter.getId() != null) {
			criteria.add(Restrictions.eq("id", filter.getId()));
		}
		
		if (!StringUtils.isEmpty(filter.getDescripcion())) {
			criteria.add(Restrictions.eq("descripcion", filter.getDescripcion()));
		}
		
		return criteria;
	}
	
}