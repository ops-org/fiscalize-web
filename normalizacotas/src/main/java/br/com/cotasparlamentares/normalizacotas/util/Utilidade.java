package br.com.cotasparlamentares.normalizacotas.util;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.cotasparlamentares.normalizacotas.domain.SingletonParametros;

public class Utilidade {

	// Log
	private static final String LOGGER_PADRAO = "br.com.cotasparlamentares.normalizacotas";
	private static final int LOG_LIMIT = 1024 * 1024; // 1Mb tamanho max do log
	private static final int LOG_FILE_COUNT = 20; // arquivos de log, no max
	
	public static final long LOG_INTERVAL = 1000; // 1 segundo de intervalo entre logs enquanto executa leitura e escrita
	
	private static Logger logger = null;
	
	public static Logger getLogger() {
		if(logger==null) {
			logger = Logger.getLogger(Utilidade.LOGGER_PADRAO);
			logger.setUseParentHandlers(false);
			
			try {
				Handler[] handlers = logger.getHandlers();
				for(Handler handler : handlers) {
				        if(handler.getClass() == ConsoleHandler.class)
				            logger.removeHandler(handler);
				}
				
				SingletonParametros sParametros = SingletonParametros.getInstance();
				
				FileHandler fileHandler = new FileHandler(sParametros.getCaminhoLog(), LOG_LIMIT, LOG_FILE_COUNT, false);
				fileHandler.setLevel(Level.INFO);
				fileHandler.setEncoding("UTF-8");
				fileHandler.setFormatter(new CustomFormatter());
				
				logger.addHandler(fileHandler);
				
				ConsoleHandler consoleHandler = new ConsoleHandler();
				consoleHandler.setLevel(Level.ALL);
				consoleHandler.setEncoding("UTF-8");
				consoleHandler.setFormatter(new CustomFormatter());
				
				logger.addHandler(consoleHandler);
				
			} catch (IOException ioe) {
				logger.log(Level.WARNING, "Impossível gravar arquivos de log!");
			}
		}
		return logger;
	}
	
}
