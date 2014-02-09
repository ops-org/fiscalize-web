package br.com.cotasparlamentares.normalizacotas.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotasparlamentares.normalizacotas.business.NormalizaCotasParlamentares;
import br.com.cotasparlamentares.normalizacotas.business.SalvarImagens;
import br.com.cotasparlamentares.normalizacotas.domain.SingletonParametros;
import br.com.cotasparlamentares.normalizacotas.util.Utilidade;

@Service
public class NormalizaCotasService {

	private Logger logger = Utilidade.getLogger(); 
	
	@Autowired
	private NormalizaCotasParlamentares normalizaCotasParlamentares;
	
	@Autowired
	private SalvarImagens salvarImagens;
	
	@PostConstruct
	public void init() {
		// Normaliza banco de dados para o site cotas parlamentares			 
		//normalizaCotasParlamentares.normalizarBancoDados();
		
		String diretorioImagens = SingletonParametros.getInstance().getCaminhoImagens();
		if(diretorioImagens!=null) {
			salvarImagens.salvar(diretorioImagens);
		}
		
		logger.log(Level.INFO, "Fim do processo! Normalização gerada com sucesso!");
	}
}
