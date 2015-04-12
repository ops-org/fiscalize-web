package br.net.ops.fiscalize.dao;

import org.springframework.stereotype.Repository;

import br.net.ops.fiscalize.domain.Despesa;

@Repository
public class DespesaDao extends HibernateGenericDao<Despesa, Integer> {

}
