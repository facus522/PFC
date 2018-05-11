package com.fsalmeron.encuestasfcm.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T extends Persistence<PK>, PK extends Serializable> {

	public long count(final BaseFilter<PK> filter);

	public List<T> filter(BaseFilter<PK> filter);

	public List<T> findAll(String... orderBy);

	public T findById(Serializable id);

	public T getById(Serializable id);
	
	public void saveOrUpdate(T entity);

	public T merge(T entity);

	public void removeById(Serializable id);
	
	public void remove(T entity);

}
