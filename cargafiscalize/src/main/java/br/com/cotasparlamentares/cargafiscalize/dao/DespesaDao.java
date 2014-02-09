package br.com.cotasparlamentares.cargafiscalize.dao;

import org.springframework.stereotype.Repository;

import br.com.cotasparlamentares.cargafiscalize.domain.Despesa;

@Repository
public class DespesaDao extends HibernateGenericDao<Despesa, Integer> {

}
