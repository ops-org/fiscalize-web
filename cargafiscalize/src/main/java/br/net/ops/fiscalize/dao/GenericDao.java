package br.net.ops.fiscalize.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

	public abstract List<T> list();

	public abstract T findById(ID id);

	public abstract T findById(ID id, boolean lock);

	public abstract ID save(T entity);

	public abstract T saveOrUpdate(T entity);
	
	public abstract void update(T entity);
	
	public abstract void delete(T entity);
	
	public abstract T merge(T entity);
	
	public abstract void evict(T entity);

	public abstract List<T> findByExample(T objetoExemplo);

	public abstract List<T> findByExample(T objetoExemplo, String... propriedadesExcluidas);

	public abstract List<T> findByPropBetween(String nomePropriedade, Object min, Object max);

	public abstract void beginTransaction();

	public abstract void commit();

	public abstract void rollback();

}