package br.net.ops.fiscalize.exception;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class ParseXMLDespesaException extends ExceptionBase {

	private static final String MENSAGEM = "Não foi possível realizar o parse do xml de carga! Verifique a integridade do arquivo!";
	
	public ParseXMLDespesaException() {
		super(MENSAGEM);
		logger.log(Level.SEVERE, MENSAGEM);
	}
	
	public ParseXMLDespesaException(Throwable e) {
		super(MENSAGEM);
		logger.log(Level.SEVERE, MENSAGEM);
		logger.log(Level.SEVERE, e.getMessage());
	}
	
}
