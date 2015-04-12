package br.net.ops.fiscalize.exception;

import java.util.logging.Level;

@SuppressWarnings("serial")
public class CargaDespesaException extends ExceptionBase {

	private static final String MENSAGEM = "Não foi possível criar o script de saída! Verifique a integridade do arquivo/diretório de saída!";
	
	public CargaDespesaException() {
		super(MENSAGEM);
		logger.log(Level.SEVERE, MENSAGEM);
	}
	
	public CargaDespesaException(Throwable e) {
		super(MENSAGEM);
		logger.log(Level.SEVERE, MENSAGEM);
		logger.log(Level.SEVERE, e.getMessage());
	}
	
}
