package com.lsikh.unlmaps.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lsikh.unlmaps.base.BaseDaoImpl;
import com.lsikh.unlmaps.base.BaseFilter;
import com.lsikh.unlmaps.dao.TipoDependenciaDao;
import com.lsikh.unlmaps.filter.TipoDependenciaFilter;
import com.lsikh.unlmaps.model.TipoDependencia;
import com.lsikh.unlmaps.utils.BooleanUtils;

@Repository
public class TipoDependenciaDaoImpl extends BaseDaoImpl<TipoDependencia, Integer> implements TipoDependenciaDao{

	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		TipoDependenciaFilter filter = (TipoDependenciaFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (BooleanUtils.isTrue(filter.getEsVisible())) {
			criteria.add(Restrictions.eq("esVisible", filter.getEsVisible()));
		}
		
		if (filter.getDescripcion() != null) {
			criteria.add(Restrictions.eq("descripcion", filter.getDescripcion()));
		}
		
		if (filter.getId() != null) {
			criteria.add(Restrictions.eq("id", filter.getId()));
		}
		
		return criteria;
	}

}
