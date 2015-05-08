package br.net.ops.fiscalize.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.net.ops.fiscalize.dao.NotaFiscalDao;
import br.net.ops.fiscalize.domain.NotaFiscal;
import br.net.ops.fiscalize.webutil.Utilidade;
import br.net.ops.fiscalize.webutil.base.ServiceBase;

@Service
public class RestService extends ServiceBase {

	@Autowired
	private NotaFiscalDao notaFiscalDao;

	private Logger logger = Utilidade.getLogger();

	@Transactional
	public NotaFiscal recuperarNotaFiscal() {
		logger.log(Level.CONFIG, "Recuperando nota fiscal...");
		return notaFiscalDao.pegarRandomica();
	}
	
}
