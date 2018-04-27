package com.fsalmeron.encuestasfcm.base;

import java.io.Serializable;
import java.util.Collection;

public interface BaseService<T extends Persistence<PK>, PK extends Serializable> {

	Collection<T> filter(BaseFilter<PK> filter);
	
	T findById(Serializable id);
	
	T getById(Serializable id);

	Collection<T> findAll(String... orderBy);
	
	void saveOrUpdate(T entity);

	T filterUnique(BaseFilter<PK> filter);

}