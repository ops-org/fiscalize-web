package br.net.ops.fiscalize.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.net.ops.fiscalize.domain.NotaFiscal;
import br.net.ops.fiscalize.pojo.PedidoNota;

@Repository
public class NotaFiscalDao extends HibernateGenericDao<NotaFiscal, Integer> {

	public NotaFiscal pegarRandomica(PedidoNota pedidoNota, int usuarioId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NotaFiscal.class);
		criteria.add(Restrictions.sqlRestriction("notaFiscalId NOT IN (SELECT notaFiscalId FROM Suspeita WHERE usuarioId=" + usuarioId + ") "));
		if(pedidoNota.getParlamentarId()!=0) {
			criteria.add(Restrictions.sqlRestriction("parlamentarId = " + pedidoNota.getParlamentarId() + " ORDER BY RAND()"));
		} else if(pedidoNota.getPartidoId()!=0) {
			criteria.add(Restrictions.sqlRestriction("partidoId = " + pedidoNota.getPartidoId() + " ORDER BY RAND()"));
		}
		criteria.setMaxResults(1);
		return (NotaFiscal) criteria.uniqueResult();
	}
	
}
