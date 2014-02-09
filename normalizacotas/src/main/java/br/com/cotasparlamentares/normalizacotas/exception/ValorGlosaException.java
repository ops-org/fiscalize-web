package br.com.cotasparlamentares.normalizacotas.exception;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class ValorGlosaException extends ExceptionBase {

	private static final String MENSAGEM = "Problemas ao resgatar a coluna Valor (vlrDocumento)!";
	
	public ValorGlosaException() {
		super(MENSAGEM);
		logger.log(Level.SEVERE, MENSAGEM);
	}
	
	public ValorGlosaException(Throwable e) {
		super(MENSAGEM);
		logger.log(Level.SEVERE, MENSAGEM);
		logger.log(Level.SEVERE, e.getMessage());
	}
	
}
