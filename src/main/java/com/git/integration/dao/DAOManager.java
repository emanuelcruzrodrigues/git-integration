package com.git.integration.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

@Component
public class DAOManager {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public <T> T save(T entity){
		entityManager.persist(entity);
		return entity;
	}

	public void flush() {
		entityManager.flush();
	}

	public <T> void delete(T entity) {
		entity = entityManager.contains(entity) ? entity : entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public <T> T load(Class<T> beanClass, Object id) {
		return (T) entityManager.find(beanClass, id);
	}
	
	public <T> List<T> query(StringBuilder hql) {
		return query(hql.toString());
	}
	
	public <T> List<T> query(String hql) {
		return query(hql, new HashMap<>());
	}
	
	public <T> List<T> query(final StringBuilder hql, final Map<String,Object> parameters) {
		return query(hql.toString(), parameters);
	}
	
	public <T> List<T> query(final String hql, final Map<String,Object> parameters) {
		return query(hql, parameters, null);
	}
	
	public <T> List<T> query(Class<T> classRetorno, StringBuilder hql, Map<String, Object> parameters) {
		return query(classRetorno, hql.toString(), parameters);
	}
	
	public <T> List<T> query(Class<T> classRetorno, String hql, Map<String, Object> parameters) {
		return query(classRetorno, hql, parameters, null);
	}
	
	public <T> List<T> query(final StringBuilder hql, final Map<String,Object> parameters, Integer maxResults) {
		return query(hql.toString(), parameters, maxResults);
	}
	
	public <T> List<T> query(Class<T> classRetorno, final StringBuilder hql, final Map<String,Object> parameters, Integer maxResults) {
		return query(classRetorno, hql.toString(), parameters, maxResults);
	}
	
	public <T> List<T> query(final String hql, final Map<String,Object> parameters, Integer maxResults) {
		return query(null, hql, parameters, maxResults);
	}
	
	@SuppressWarnings({"unchecked"})
	public <T> List<T> query(final Class<T> classRetorno, final String hql, final Map<String,Object> parameters, Integer maxResults) {
		Session session = getSession();
		Query q = session.createQuery(hql);

		if(maxResults != null){
			q.setMaxResults(maxResults);
		}

		addTransformer(classRetorno, q);

		updateParameters(q, parameters);

		return q.list();
	}

	private void updateParameters(Query query, final Map<String, Object> parameters) {
		String[] namedParameters = query.getNamedParameters();
		
		if (namedParameters != null) {
			for (int i = 0; i < namedParameters.length; i++) {
				Object value = parameters.get(namedParameters[i]);
				if (value instanceof Object[]) {
					value = Arrays.asList((Object[])value);
				}
				
				if (value instanceof Collection<?>) {
					query.setParameterList(namedParameters[i], (Collection<?>) value);
				} else {
					query.setParameter(namedParameters[i], value);
				}
			}
		}
	}	
	
	private void addTransformer(Class<?> classRetorno, Query query) {
		if (classRetorno != null){
			query.setResultTransformer(Transformers.aliasToBean(classRetorno));
		}
	}

	public void executeUpdate(String hql, Map<String, Object> parameters) {
		Session session = getSession();
		Query query = session.createQuery(hql);

		updateParameters(query, parameters);

		query.executeUpdate();
	}
	
	public <T> T uniqueResult(StringBuilder hql, Map<String, Object> parameters) {
		return uniqueResult(hql.toString(), parameters);
	}

	public <T> T uniqueResult(String hql, Map<String, Object> params) {
		Session session = getSession();
		Query query = session.createQuery(hql);

		updateParameters(query, params);

		@SuppressWarnings("unchecked")
		T result = (T) query.uniqueResult();
		return result;
	}

	public void merge(Object entity) {
		entityManager.merge(entity);
	}
	
	public void clearSession() {
		Session session = getSession();
		session.clear();
	}

	public Session getSession() {
		Session session = (Session) entityManager.getDelegate();
		return session;
	}


}
