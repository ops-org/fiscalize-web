package br.net.ops.fiscalize.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.net.ops.fiscalize.domain.Despesa;
import br.net.ops.fiscalize.domain.NotaFiscal;
import br.net.ops.fiscalize.domain.Parlamentar;

@Repository
public class ParlamentarDao extends HibernateGenericDao<Parlamentar, Integer> {

}
