package com.tickettracker.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pooja Mantri
 */
@Repository
@Transactional
public abstract class GenericDAOImpl<T, ID extends Serializable> implements
		GenericDAO<T, ID> {

	@PersistenceContext
	protected EntityManager em;

	protected final Class<T> entityClass;

	protected GenericDAOImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public T findById(ID id) {
		return findById(id, LockModeType.NONE);
	}

	public T findById(ID id, LockModeType lockModeType) {
		return em.find(entityClass, id, lockModeType);
	}

	public T findReferenceById(ID id) {
		return em.getReference(entityClass, id);
	}

	public List<T> findAll() {
		CriteriaQuery<T> c = em.getCriteriaBuilder().createQuery(entityClass);
		c.select(c.from(entityClass));
		return em.createQuery(c).getResultList();
	}

	public Long getCount() {
		CriteriaQuery<Long> c = em.getCriteriaBuilder().createQuery(Long.class);
		c.select(em.getCriteriaBuilder().count(c.from(entityClass)));
		return em.createQuery(c).getSingleResult();
	}

	public T merge(T instance) {
		return em.merge(instance);
	}

	public void delete(T entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	public void deleteById(ID id) {
		T entity = findById(id);
		delete(entity);
	}
}