package br.net.ops.fiscalize.webutil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.net.ops.fiscalize.util.CustomFormatter;

public class Utilidade {

	// CONSTANTES #################################################################

	// LOGGER
	public static final String LOGGER_PADRAO = "br.net.ops.fiscalize";
	
	// FORMATO DATA
	public static final String FORMATO_DATA = "dd/MM/yyyy HH:mm:ss";
	
	// HEADER HTTP
	public static final String HTTP_HEADER_JSON = "application/json";
	
	public static Logger logger = null;
	
	public static String formatarData(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
		return sdf.format(data);
	}
	
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
				
				/*File file = new File(DIRETORIO_LOG);
				if(!file.exists()) file.mkdirs();
				
				int limit = 1024 * 1024; // 1 Mb 
				int count = 50; 
				FileHandler fileHandler = new FileHandler(DIRETORIO_LOG, limit, count, false);
				fileHandler.setLevel(Level.CONFIG);
				fileHandler.setEncoding("UTF-8");
				fileHandler.setFormatter(new CustomFormatter());
				
				logger.addHandler(fileHandler);*/
				
				ConsoleHandler consoleHandler = new ConsoleHandler();
				consoleHandler.setLevel(Level.INFO);
				consoleHandler.setEncoding("UTF-8");
				consoleHandler.setFormatter(new CustomFormatter());
				
				logger.addHandler(consoleHandler);
				
			} catch (IOException ioe) {
				logger.log(Level.WARNING, "Impossível gravar arquivos de log! Verifique o diretório de instalação!");
			}
		}
		
		return logger;
	}
	
	/*public static Logger getLogger() {
		return Logger.getLogger(Utilidade.LOGGER_PADRAO);
	}*/
	
	public static Logger getLogger(String resourceBundle) {
		return Logger.getLogger(Utilidade.LOGGER_PADRAO, resourceBundle);
	}
	
}
	
	
 