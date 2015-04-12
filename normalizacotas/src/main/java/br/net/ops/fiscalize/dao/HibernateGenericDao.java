package br.net.ops.fiscalize.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HibernateGenericDao<T, ID extends Serializable> implements GenericDao<T, ID> {
	
	// ATRIBUTOS ##################################################################
	@Autowired(required=true)
	protected SessionFactory sessionFactory;
	
	private Class<T> persistentClass;
	
	// METODOS ####################################################################
	@SuppressWarnings("unchecked")
	public List<T> list() {
		return this.sessionFactory.getCurrentSession().createCriteria(this.getPersistentClass()).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> list(Integer first, Integer max, String orderByProp, Boolean isAsc) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(this.getPersistentClass());
		
		if(first != null)
			crit.setFirstResult(first);
		
		if(max != null)
			crit.setMaxResults(max);
		
		if(orderByProp != null && !orderByProp.isEmpty()){
			if(isAsc == null)
				throw new IllegalArgumentException("se o argumento 'String orderByProp' nao for nulo, o argumento 'Boolean isAsc' tambem nao pode ser");
				
			if(isAsc)
				crit.addOrder(Order.asc(orderByProp));
			else
				crit.addOrder(Order.desc(orderByProp));
		}
		
		return crit.list();
	}
	
	public T findById(ID id) {
		return findById(id, false);
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock) {
		T entity;
		if (lock)
			entity = (T)this.sessionFactory.getCurrentSession().load(getPersistentClass(), id, LockOptions.UPGRADE);
		else
			entity = this.findByIdEm(id);
		
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public T findByIdEm(ID id) {
		return (T)this.sessionFactory.getCurrentSession().load(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T objetoExemplo){
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(this.getPersistentClass());
		Example exemplo = Example.create(objetoExemplo);
		exemplo.excludeZeroes();
		
		crit.add(exemplo);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T objetoExemplo, String... propriedadesExcluidas){
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(this.getPersistentClass());
		Example exemplo = Example.create(objetoExemplo);
		
		if(propriedadesExcluidas != null)
			for (String propExcl : propriedadesExcluidas) 
				exemplo.excludeProperty(propExcl);
		
		crit.add(exemplo);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByPropBetween(String nomePropriedade, Object min, Object max){
		List<T> objetos = this.sessionFactory.getCurrentSession().createCriteria(this.getPersistentClass())
			.add(Restrictions.between(nomePropriedade, min, max))
			.list();
		
		return objetos;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByValueIn(String property, List<Object> values) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(this.getPersistentClass())
			.add(Restrictions.in(property, values));
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public ID save(T entity) {
		return (ID) this.sessionFactory.getCurrentSession().save(entity);
	}
	
	public T saveOrUpdate(T entity) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}
	
	public void update(T entity) {
		this.sessionFactory.getCurrentSession().update(entity);
	}
	
	public void delete(T entity) {
		this.sessionFactory.getCurrentSession().delete(entity);
	}
	
	public T merge(T entity) {
		this.sessionFactory.getCurrentSession().merge(entity);
		return entity;
	}
	
	public void beginTransaction(){
		this.sessionFactory.getCurrentSession().beginTransaction();
	}
	
	public void commit(){
		this.sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
	public void rollback(){
		this.sessionFactory.getCurrentSession().getTransaction().rollback();
	}
	
	public void flush(){
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void evict(T entity){
		this.sessionFactory.getCurrentSession().evict(entity);
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getPersistentClass() {
		if(this.persistentClass==null) {
			this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return persistentClass;
	}

}
