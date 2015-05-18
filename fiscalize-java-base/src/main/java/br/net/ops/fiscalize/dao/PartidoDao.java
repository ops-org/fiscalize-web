package br.net.ops.fiscalize.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.net.ops.fiscalize.domain.Partido;

@Repository
public class PartidoDao extends HibernateGenericDao<Partido, Integer> {

}
