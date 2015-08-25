package br.net.ops.fiscalize.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.net.ops.fiscalize.domain.NotaFiscal;
import br.net.ops.fiscalize.pojo.PedidoNota;

@Repository
public class NotaFiscalDao extends HibernateGenericDao<NotaFiscal, Integer> {

    
    @SuppressWarnings("rawtypes")
	public NotaFiscal pegarRandomica(PedidoNota pedidoNota, int usuarioId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		// Pega notaFiscalId aleatória da maneira rápida
		String sql = "SELECT notaFiscalId FROM NotaFiscal JOIN (SELECT CEIL(RAND() * (SELECT MAX(notaFiscalId) FROM NotaFiscal)) AS id) AS Random WHERE NotaFiscal.notaFiscalId >= Random.id LIMIT 1";
        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List data = query.list();
        
        NotaFiscal retorno = null;
        if(data.size()>0) {
        	Map row = (Map) data.get(0);
            
            Integer notaFiscalId = (Integer) row.get("notaFiscalId"); 
            
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NotaFiscal.class);
            if(notaFiscalId!=null) {
         	   criteria.add(Restrictions.eq("notaFiscalId", notaFiscalId));
            }
            criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY RAND()")); // se não pegou notaFiscalId, pega aleatório de maneira lenta
            criteria.setMaxResults(1);
            
            retorno = (NotaFiscal) criteria.uniqueResult();
        }
        
		return retorno;
	}
	
}
