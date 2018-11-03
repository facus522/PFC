package com.fsalmeron.encuestasfcm.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import com.fsalmeron.encuestasfcm.model.Resultado;

public abstract class BaseDaoImpl<T extends Persistence<PK>, PK extends Serializable> implements BaseDao<T, PK> {

	private Class<T> persistentClass;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public Integer getResultadosByUsuario(Integer idUsuario, Integer idEncuesta) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Resultado.class);
		criteria.add(Restrictions.eq("usuario.id", idUsuario));
		criteria.add(Restrictions.eq("idEncuesta", idEncuesta));
		Transaction tx = getSession().beginTransaction();
		List<Resultado> result = criteria.getExecutableCriteria(getSession()).list();
		tx.commit();
		return result.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) {
		Transaction tx = getSession().beginTransaction();
		T entity = (T) getSession().load(persistentClass, id);
		tx.commit();
		return entity;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public T getById(Serializable id) {
		Transaction tx = getSession().beginTransaction();
		T entity = (T) getSession().get(persistentClass, id);
		tx.commit();
		return entity;
	}

	@Override
	public long count(final BaseFilter<PK> filter) {
		Transaction tx = getSession().beginTransaction();
		DetachedCriteria criteria = createFilterCriteria(filter);
		criteria.setProjection(Projections.rowCount());
		Long result = (Long) executeCriteriaForUniqueResult(criteria);
		tx.commit();
		return result == null ? 0 : result;
	}

	public List<T> filter(BaseFilter<PK> baseFilter) {
		Transaction tx = getSession().beginTransaction();
		List<T> results = filterFullData(baseFilter);
		tx.commit();
		return results;
	}

	@Override
	public List<T> findAll(String... orderBy) {
		DetachedCriteria criteria = createDetachedCriteria();
		for (String property : orderBy) {
			criteria.addOrder(Order.asc(property));
		}
		return executeCriteria(criteria);
	}

	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(persistentClass);
	}

	@Override
	public void saveOrUpdate(T entity) {
		Transaction tx = getSession().beginTransaction();
		getSession().saveOrUpdate(entity);
		tx.commit();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected DetachedCriteria createFilterCriteria(final BaseFilter<PK> baseFilter) {
		DetachedCriteria crit = DetachedCriteria.forClass(getPersistentClass(), getFilterCriteriaAlias());
		if (baseFilter.getExcludeIds() != null && baseFilter.getExcludeIds().length > 0) {
			crit.add(Restrictions.not(Restrictions.in("id", baseFilter.getExcludeIds())));
		}
		for (Entry<String, String> alias : baseFilter.getAliases().entrySet()) {
			crit.createAlias(alias.getKey(), alias.getValue());
		}
		for (Entry<String, String> alias : baseFilter.getLeftAliases().entrySet()) {
			crit.createAlias(alias.getKey(), alias.getValue(), JoinType.LEFT_OUTER_JOIN);
		}
		if (baseFilter.getEagerRelationShips() != null) {
			for (String relation : baseFilter.getEagerRelationShips()) {
				crit.setFetchMode(relation, FetchMode.JOIN);
			}
		}
		return crit;
	}

	protected String getFilterCriteriaAlias() {
		return getPersistentClass().getSimpleName().toLowerCase();
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Criteria createCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(persistentClass);
	}


	protected void setResultTransformer(DetachedCriteria criteria) {
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);
	}

	protected Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	protected List<T> filterFullData(BaseFilter<PK> filter) {
		DetachedCriteria criteria = createFilterCriteria(filter);
		return addPagination(criteria, filter).list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> filterNotFullData(BaseFilter<PK> filter) {
		DetachedCriteria criteria = createFilterCriteria(filter);
		ProjectionList projections = null;
		if (filter.getReturnedAttributes() != null) {
			projections = Projections.projectionList();
			for (String attr : filter.getReturnedAttributes()) {
				projections.add(Projections.alias(Projections.property(attr), attr));
			}
		}
		criteria.setProjection(Projections.distinct(projections));
		criteria.setResultTransformer(new AliasToBeanResultTransformer(persistentClass));
		return addPagination(criteria, filter).list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> executeCriteria(DetachedCriteria criteria) {
		Transaction tx = (Transaction) getSession().beginTransaction();
		List<T> result = criteria.getExecutableCriteria(getSession()).list();
		tx.commit();
		return result;
	}

	protected Object executeCriteriaForUniqueResult(DetachedCriteria criteria) {
		return criteria.getExecutableCriteria(getSession()).uniqueResult();
	}

	protected Criteria addPagination(DetachedCriteria detachedCriteria, BaseFilter<PK> filter) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		if (filter.getFirstResult() != null) {
			criteria.setFirstResult(filter.getFirstResult());
		}
		if (filter.getMaxResult() != null) {
			criteria.setMaxResults(filter.getMaxResult());
		}
		return criteria;
	}

	@SuppressWarnings("unchecked")
	public T merge(T entity) {
		return (T) getSession().merge(entity);
	}

	@Override
	public void removeById(Serializable id) {
		if (id != null) {
			T entity = getById(id);
			remove(entity);
		}
		
	}

	@Override
	public void remove(T entity) {
		Transaction tx = getSession().beginTransaction();
		getSession().delete(entity);
		tx.commit();
	}
}
