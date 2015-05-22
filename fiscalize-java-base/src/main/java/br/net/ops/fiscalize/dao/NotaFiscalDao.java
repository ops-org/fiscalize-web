package br.net.ops.fiscalize.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.net.ops.fiscalize.domain.NotaFiscal;

@Repository
public class NotaFiscalDao extends HibernateGenericDao<NotaFiscal, Integer> {

	public NotaFiscal pegarRandomica() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NotaFiscal.class);
		criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY RAND()"));
		criteria.setMaxResults(1);
		return (NotaFiscal) criteria.uniqueResult();
	}
	
}
