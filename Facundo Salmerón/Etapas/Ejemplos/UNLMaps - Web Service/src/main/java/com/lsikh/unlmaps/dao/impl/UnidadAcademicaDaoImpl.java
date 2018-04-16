package com.lsikh.unlmaps.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.lsikh.unlmaps.base.BaseDaoImpl;
import com.lsikh.unlmaps.base.BaseFilter;
import com.lsikh.unlmaps.dao.UnidadAcademicaDao;
import com.lsikh.unlmaps.filter.UnidadAcademicaFilter;
import com.lsikh.unlmaps.model.UnidadAcademica;

@Repository
public class UnidadAcademicaDaoImpl extends BaseDaoImpl<UnidadAcademica, Integer> implements UnidadAcademicaDao{
	
	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		UnidadAcademicaFilter filter = (UnidadAcademicaFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (!StringUtils.isEmpty(filter.getNombre())) {
			criteria.add(Restrictions.eq("nombre", filter.getNombre()));
		}
		
		return criteria;
	}

}
