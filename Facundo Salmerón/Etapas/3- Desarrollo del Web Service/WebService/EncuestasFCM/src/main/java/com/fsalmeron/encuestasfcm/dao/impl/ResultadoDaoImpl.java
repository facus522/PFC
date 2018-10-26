package com.fsalmeron.encuestasfcm.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fsalmeron.encuestasfcm.base.BaseDaoImpl;
import com.fsalmeron.encuestasfcm.base.BaseFilter;
import com.fsalmeron.encuestasfcm.dao.ResultadoDao;
import com.fsalmeron.encuestasfcm.filter.ResultadoFilter;
import com.fsalmeron.encuestasfcm.model.Resultado;

@Repository
public class ResultadoDaoImpl extends BaseDaoImpl<Resultado, Integer> implements ResultadoDao{

	@Override
	protected DetachedCriteria createFilterCriteria(BaseFilter<Integer> baseFilter) {
		ResultadoFilter filter = (ResultadoFilter) baseFilter;
		DetachedCriteria criteria = super.createFilterCriteria(baseFilter);
		
		if (filter.getId() != null) {
			criteria.add(Restrictions.eq("id", filter.getId()));
		}
		
		if (!StringUtils.isEmpty(filter.getLatitud())) {
			criteria.add(Restrictions.eq("latitud", filter.getLatitud()));
		}
		
		if (!StringUtils.isEmpty(filter.getLongitud())) {
			criteria.add(Restrictions.eq("longitud", filter.getLongitud()));
		}
		
		if (filter.getEdadEncuestado() != null) {
			criteria.add(Restrictions.eq("edadEncuestado", filter.getEdadEncuestado()));
		}
		
		if (filter.getSexoEncuestado() != null) {
			criteria.add(Restrictions.eq("sexoEncuestado", filter.getSexoEncuestado()));
		}
		
		if (filter.getUsuario() != null) {
			criteria.add(Restrictions.eq("usuario", filter.getUsuario()));
		}
		
		if (filter.getRespuesta() != null) {
			criteria.add(Restrictions.eq("respuesta", filter.getRespuesta()));
		}
		return criteria;
	}
	
}