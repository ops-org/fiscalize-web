package br.com.cotasparlamentares.normalizacotas.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.cotasparlamentares.normalizacotas.domain.Despesa;
import br.com.cotasparlamentares.normalizacotas.domain.NotaFiscal;
import br.com.cotasparlamentares.normalizacotas.domain.Parlamentar;

@Repository
public class ParlamentarDao extends HibernateGenericDao<Parlamentar, Integer> {

	/*public List<Parlamentar> selecionarParlamentares() {
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM CATS WHERE NAME like ?").addEntity(Cat.class);
		List pusList = query.setString(0, "Pus%").list();
		
	}*/
	
}
