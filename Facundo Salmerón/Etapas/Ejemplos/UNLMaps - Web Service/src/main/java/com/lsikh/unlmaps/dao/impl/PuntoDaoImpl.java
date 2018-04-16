package com.lsikh.unlmaps.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lsikh.unlmaps.base.BaseDaoImpl;
import com.lsikh.unlmaps.base.BaseFilter;
import com.lsikh.unlmaps.dao.PuntoDao;
import com.lsikh.unlmaps.filter.PuntoFilter;
import com.lsikh.unlmaps.model.Punto;

@Repository
public class PuntoDaoImpl extends BaseDaoImpl<Punto, Integer> implements PuntoDao{
	
	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		PuntoFilter filter = (PuntoFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (filter.getEdificio() != null) {
			criteria.add(Restrictions.eq("edificio", filter.getEdificio()));
		}
		
		if (filter.getUnidadAcademica() != null) {
			criteria.add(Restrictions.eq("unidadAcademica", filter.getUnidadAcademica()));
		}
		
		if (filter.getTipoDependencia() != null) {
			criteria.add(Restrictions.eq("tipoDependencia", filter.getTipoDependencia()));
		}
		
		return criteria;
	}

}
