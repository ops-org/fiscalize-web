package br.net.ops.fiscalize.exception;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class DespesaReflectionException extends ExceptionBase {

	private static final String MENSAGEM = "Não foi possível popular o objeto Despesa com base no XML fornecido! Verifique a consistência dos dados!";
	
	public DespesaReflectionException() {
		super(MENSAGEM);
		logger.log(Level.SEVERE, MENSAGEM);
	}
	
	public DespesaReflectionException(Throwable e) {
		super(MENSAGEM);
		logger.log(Level.SEVERE, MENSAGEM);
		logger.log(Level.SEVERE, e.getMessage());
	}
	
}
