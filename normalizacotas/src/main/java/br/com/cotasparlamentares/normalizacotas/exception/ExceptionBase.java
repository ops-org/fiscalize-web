package br.com.cotasparlamentares.normalizacotas.exception;

import java.util.logging.Logger;

import br.com.cotasparlamentares.normalizacotas.util.Utilidade;

@SuppressWarnings("serial")
public class ExceptionBase extends Throwable {

	protected Logger logger = Utilidade.getLogger();
	
	public ExceptionBase(String mensagem) {
		super(mensagem);
	}
	
}
