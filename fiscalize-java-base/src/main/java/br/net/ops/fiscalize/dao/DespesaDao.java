package br.net.ops.fiscalize.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.net.ops.fiscalize.domain.Despesa;

@Repository
public class DespesaDao extends HibernateGenericDao<Despesa, Integer> {

	@Override
	@SuppressWarnings("unchecked")
	public List<Despesa> list() {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findAllDespesas");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> selecionarPartidos() {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT sgPartido as sigla FROM Despesa");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> selecionarParlamentares() {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT txNomeParlamentar as nomeParlamentar, sgPartido as sigla, ideCadastro FROM Despesa");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> selecionarCotas() {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT txtDescricao as cota FROM Despesa");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> selecionarUfs() {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT sgUF as uf FROM Despesa");
		return query.list();
	}
	
}
