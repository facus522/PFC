package com.fsalmeron.encuestasfcm.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fsalmeron.encuestasfcm.base.BaseDaoImpl;
import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.dao.RespuestaDao;
import com.fsalmeron.encuestasfcm.filter.RespuestaFilter;
import com.fsalmeron.encuestasfcm.model.Respuesta;

@Repository
public class RespuestaDaoImpl extends BaseDaoImpl<Respuesta, Integer> implements RespuestaDao{

	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		RespuestaFilter filter = (RespuestaFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (filter.getId() != null) {
			criteria.add(Restrictions.eq("id", filter.getId()));
		}
		
		if (!StringUtils.isEmpty(filter.getDescripcion())) {
			criteria.add(Restrictions.eq("descripcion", filter.getDescripcion()));
		}
		
		if (filter.getTipoRespuesta() != null) {
			criteria.add(Restrictions.eq("tipoRespuesta", filter.getTipoRespuesta()));
		}
		
		if (filter.getEncuestaPregunta() != null) {
			criteria.add(Restrictions.eq("encuestaPregunta", filter.getEncuestaPregunta()));
		}
		
		return criteria;
	}
	
}