package com.fsalmeron.encuestasfcm.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl<T extends Persistence<PK>, PK extends Serializable> implements BaseService<T, PK> {

	@Autowired
	protected BaseDao<T, PK> dao;

	@Override
	public Collection<T> filter(BaseFilter<PK> filter) {
		return dao.filter(filter);
	}

	@Override
	public T findById(Serializable id) {
		return dao.findById(id);
	}

	@Override
	public Collection<T> findAll(String... orderBy) {
		return dao.findAll(orderBy);
	}

	@Override
	public void saveOrUpdate(T entity) {
		dao.saveOrUpdate(entity);
	}

	public BaseDao<T, PK> getDao() {
		return dao;
	}

	public void setDao(BaseDao<T, PK> dao) {
		this.dao = dao;
	}

	@Override
	public T getById(Serializable id) {
		return dao.getById(id);
	}
	
	@Override
	public T filterUnique(BaseFilter<PK> filter) {
		List<T> results = (List<T>) filter(filter);
		return results.isEmpty() ? null : results.get(0);
	}
	
	@Override
	public void removeById(Serializable id) {
		dao.removeById(id);
	}

	@Override
	public void remove(T entity) {
		dao.remove(entity);
	}

}
