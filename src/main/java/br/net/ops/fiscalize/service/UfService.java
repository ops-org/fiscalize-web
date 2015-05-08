package br.net.ops.fiscalize.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.net.ops.fiscalize.dao.UfDao;
import br.net.ops.fiscalize.domain.Uf;
import br.net.ops.fiscalize.utils.Utilidade;
import br.net.ops.fiscalize.utils.base.ServiceBase;

@Service
public class UfService extends ServiceBase {

	@Autowired
	private UfDao ufDao;

	private Logger logger = Utilidade.getLogger();

	@Transactional
	public int numeroUfs() {
		logger.log(Level.CONFIG, "Pesquisando numero de UFs...");
		List<Uf> ufs = ufDao.list();
		return ufs.size();
	}
	
}
