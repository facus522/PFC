package com.fsalmeron.encuestasfcm.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fsalmeron.encuestasfcm.base.BaseDaoImpl;
import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.dao.EncuestaDao;
import com.fsalmeron.encuestasfcm.filter.EncuestaFilter;
import com.fsalmeron.encuestasfcm.model.Encuesta;

@Repository
public class EncuestaDaoImpl extends BaseDaoImpl<Encuesta, Integer> implements EncuestaDao{

	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		EncuestaFilter filter = (EncuestaFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (filter.getId() != null) {
			criteria.add(Restrictions.eq("id", filter.getId()));
		}
		
		if (!StringUtils.isEmpty(filter.getTitulo())) {
			criteria.add(Restrictions.eq("titulo", filter.getTitulo()));
		}
		
		if (!StringUtils.isEmpty(filter.getDescripcion())) {
			criteria.add(Restrictions.eq("descripcion", filter.getDescripcion()));
		}

		if (filter.getActivo() != null) {
			criteria.add(Restrictions.eq("activo", filter.getActivo()));
		}
		
		if (filter.getIdUsuarioAlta() != null) {
			criteria.add(Restrictions.eq("idUsuarioAlta", filter.getIdUsuarioAlta()));
		}
		
		if (filter.getIdUsuarioModificacion() != null) {
			criteria.add(Restrictions.eq("idUsuarioModificacion", filter.getIdUsuarioModificacion()));
		}
		
		if (filter.getIdUsuarioEliminacion() != null) {
			criteria.add(Restrictions.eq("idUsuarioEliminacion", filter.getIdUsuarioEliminacion()));
		}
		
		if (filter.getFechaAlta() != null) {
			criteria.add(Restrictions.eq("fechaAlta", filter.getFechaAlta()));
		}
		
		if (filter.getFechaModificacion() != null) {
			criteria.add(Restrictions.eq("fechaModificacion", filter.getFechaModificacion()));
		}
		
		if (filter.getFechaEliminacion() != null) {
			criteria.add(Restrictions.eq("fechaEliminacion", filter.getFechaEliminacion()));
		}
		
		return criteria;
	}
	
}
