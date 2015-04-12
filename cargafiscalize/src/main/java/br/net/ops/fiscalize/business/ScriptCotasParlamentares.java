package br.net.ops.fiscalize.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import br.net.ops.fiscalize.domain.Despesa;
import br.net.ops.fiscalize.domain.SingletonParametros;
import br.net.ops.fiscalize.exception.CargaDespesaException;
import br.net.ops.fiscalize.exception.DespesaReflectionException;
import br.net.ops.fiscalize.util.Utilidade;

@Component
public class ScriptCotasParlamentares {

	private static final String ENCODING_SAIDA = "UTF-8";
	
	private File arquivoSaida; 
	
	private Logger logger;
	
	private long inicio;
	
	public ScriptCotasParlamentares(){
		this.inicio = System.currentTimeMillis();
		this.logger = Utilidade.getLogger();
		
		String strCaminhoSql = SingletonParametros.getInstance().getCaminhoSql();
		
		this.arquivoSaida = new File(strCaminhoSql);
	}
	
	public void gerarCarga(List<Despesa> despesas) throws CargaDespesaException, DespesaReflectionException {
		logger.log(Level.INFO, "Iniciando criação dos scripts SQL de carga...");
		
		// Cria arquivo de saída para escrita
		try {
			if(arquivoSaida.exists()) {
				logger.log(Level.WARNING, "Arquivo já existe! Apagando para gerar nova carga!");
				arquivoSaida.delete();
			}
			arquivoSaida.createNewFile();
		} catch(IOException e) {
			throw new CargaDespesaException(e);
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivoSaida), ENCODING_SAIDA));

			String quebraLinha = System.getProperty("line.separator");
			
			long ultimoLog = System.currentTimeMillis();
			int iteracao = 0;
			for(Despesa despesa:despesas) { // loop em todas as despesas
				
					if(iteracao==0) { // se primeiro loop, insere CREATE TABLE 
						bw.write(despesa.retornarCreateSQL() + quebraLinha + quebraLinha); 
					}
					bw.write(despesa.retornarInsertSQL() + quebraLinha);
				
					iteracao++;
					if(System.currentTimeMillis() - ultimoLog > Utilidade.LOG_INTERVAL) { // imprime log apenas em intervalo de tempo pré-definido
						logger.log(Level.INFO, "Escrevendo registro " + iteracao);
						ultimoLog = System.currentTimeMillis();
					}
			}
			bw.flush();
			bw.close();
			
			logger.log(Level.INFO, "Tempo de escrita: " + (System.currentTimeMillis()-inicio)/1000 + " segundos");
	        logger.log(Level.INFO, "Registros escritos: " + iteracao);
	        
		} catch(IOException e) {
			throw new CargaDespesaException(e);
		}
		
	}
	
}