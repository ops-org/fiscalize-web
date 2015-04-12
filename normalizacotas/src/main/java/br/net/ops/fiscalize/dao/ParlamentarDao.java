package br.net.ops.fiscalize.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.net.ops.fiscalize.domain.Despesa;
import br.net.ops.fiscalize.domain.NotaFiscal;
import br.net.ops.fiscalize.domain.Parlamentar;

@Repository
public class ParlamentarDao extends HibernateGenericDao<Parlamentar, Integer> {

	/*public List<Parlamentar> selecionarParlamentares() {
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM CATS WHERE NAME like ?").addEntity(Cat.class);
		List pusList = query.setString(0, "Pus%").list();
		
	}*/
	
}
