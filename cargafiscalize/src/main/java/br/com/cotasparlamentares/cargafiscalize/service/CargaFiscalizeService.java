package br.com.cotasparlamentares.cargafiscalize.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotasparlamentares.cargafiscalize.business.DatabaseCotasParlamentares;
import br.com.cotasparlamentares.cargafiscalize.business.ParseXmlCotasParlamentares;
import br.com.cotasparlamentares.cargafiscalize.business.ScriptCotasParlamentares;
import br.com.cotasparlamentares.cargafiscalize.domain.Despesa;
import br.com.cotasparlamentares.cargafiscalize.domain.SingletonParametros;
import br.com.cotasparlamentares.cargafiscalize.exception.CargaDespesaException;
import br.com.cotasparlamentares.cargafiscalize.exception.DespesaReflectionException;
import br.com.cotasparlamentares.cargafiscalize.exception.ParseXMLDespesaException;
import br.com.cotasparlamentares.cargafiscalize.util.Utilidade;

@Service
public class CargaFiscalizeService {

	private Logger logger = Utilidade.getLogger(); 
	
	@Autowired
	private ParseXmlCotasParlamentares parseXmlCotasParlamentares;
	
	@Autowired
	private ScriptCotasParlamentares scriptCotasParlamentares;
	
	@Autowired
	private DatabaseCotasParlamentares databaseCotasParlamentares;	
	
	@PostConstruct
	public void init() {
		try {
			
			// Parse XML
			List<Despesa> despesas = parseXmlCotasParlamentares.parse();

			if(SingletonParametros.getInstance().getTipo().equalsIgnoreCase(SingletonParametros.TIPO_SCRIPT)) {
				// Cria Scripts SQL
				scriptCotasParlamentares.gerarCarga(despesas);
			} else {
				// Salva em banco de dados
				databaseCotasParlamentares.carregarBancoDados(despesas);	
			}
			
			logger.log(Level.INFO, "Fim do processo! Carga gerada com sucesso!");
		} catch(ParseXMLDespesaException e) {
			logger.log(Level.SEVERE, "Não foi possível carregar o xml com os dados das cotas parlamentares!");
		} catch(CargaDespesaException | DespesaReflectionException e) {
			logger.log(Level.SEVERE, "Não foi possível criar o sql com os dados das cotas parlamentares!");
		}
	}
}
