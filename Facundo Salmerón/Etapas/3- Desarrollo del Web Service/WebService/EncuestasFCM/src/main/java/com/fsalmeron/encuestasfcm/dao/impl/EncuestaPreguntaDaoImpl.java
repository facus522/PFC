package com.fsalmeron.encuestasfcm.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.fsalmeron.encuestasfcm.base.BaseDaoImpl;
import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.dao.EncuestaPreguntaDao;
import com.fsalmeron.encuestasfcm.filter.EncuestaPreguntaFilter;
import com.fsalmeron.encuestasfcm.model.EncuestaPregunta;

@Repository
public class EncuestaPreguntaDaoImpl extends BaseDaoImpl<EncuestaPregunta, Integer> implements EncuestaPreguntaDao{

	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		EncuestaPreguntaFilter filter = (EncuestaPreguntaFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (filter.getId() != null) {
			criteria.add(Restrictions.eq("id", filter.getId()));
		}
		
		if (filter.getEncuesta() != null) {
			criteria.add(Restrictions.eq("encuesta", filter.getEncuesta()));
		}
		
		if (filter.getPregunta() != null) {
			criteria.add(Restrictions.eq("pregunta", filter.getPregunta()));
		}
		return criteria;
	}
	
}