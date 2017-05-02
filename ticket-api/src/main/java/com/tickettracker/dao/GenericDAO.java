package com.tickettracker.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.LockModeType;

/**
 * An interface shared by all business data access objects.
 * All CRUD (create, read, update, delete) basic data access operations are
 * isolated in this interface and shared across all DAO implementations.
 */

/**
 * @author Pooja Mantri
 */
public interface GenericDAO<T, ID extends Serializable> {

	T findById(ID id);

	T findById(ID id, LockModeType lockModeType);

	T findReferenceById(ID id);

	List<T> findAll();

	Long getCount();

	T merge(T entity);

	void delete(T entity);

	void deleteById(ID id);
}