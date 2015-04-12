package br.net.ops.fiscalize.business;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.net.ops.fiscalize.dao.DespesaDao;
import br.net.ops.fiscalize.domain.Despesa;
import br.net.ops.fiscalize.exception.CargaDespesaException;
import br.net.ops.fiscalize.exception.DespesaReflectionException;
import br.net.ops.fiscalize.util.Utilidade;

@Component
public class DatabaseCotasParlamentares {

	private Logger logger;
	
	private long inicio;
	
	@Autowired
	private DespesaDao despesaDao;
	
	public DatabaseCotasParlamentares() {
		this.inicio = System.currentTimeMillis();
		this.logger = Utilidade.getLogger();
	}
	
	@Transactional
	public void carregarBancoDados(List<Despesa> despesas) throws CargaDespesaException, DespesaReflectionException {
		logger.log(Level.INFO, "Iniciando leitura e salvamento dos registros no banco de dados...");
		
		long ultimoLog = System.currentTimeMillis();
		int iteracao=0;
		int validos=0;
		for(Despesa despesa:despesas) {
			try {
				List<Despesa> despesasExistentes = despesaDao.findByExample(despesa);
				if(despesasExistentes.size()==0) {
					despesaDao.save(despesa);	
				} else {
					logger.log(Level.WARNING, "Despesa " + iteracao + " já existe, ignorada!");
				}
				validos++;
			} catch(HibernateException e) {
				logger.log(Level.WARNING, "Erro ao salvar DESPESA: " + iteracao + " Erro: " + e.getLocalizedMessage());
				if(e.getCause()!=null) {
					logger.log(Level.WARNING, "CAUSA: " + iteracao + " Erro: " + e.getCause().getLocalizedMessage());
				}
			}
			
			iteracao++;
            if(System.currentTimeMillis() - ultimoLog > Utilidade.LOG_INTERVAL) {
            	logger.log(Level.INFO, "Lendo (e tentando salvar) registro " + iteracao);
				ultimoLog = System.currentTimeMillis();
			}
            
		}
		
		logger.log(Level.INFO, "Tempo de salvamento em banco: " + (System.currentTimeMillis()-inicio)/1000 + " segundos");
        logger.log(Level.INFO, "Registros lidos: " + iteracao);
        logger.log(Level.INFO, "Registros válidos: " + validos);
		
	}
	
}