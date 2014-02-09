package br.com.cotasparlamentares.cargafiscalize.exception;

import java.util.logging.Logger;

import br.com.cotasparlamentares.cargafiscalize.util.Utilidade;

@SuppressWarnings("serial")
public class ExceptionBase extends Throwable {

	protected Logger logger = Utilidade.getLogger();
	
	public ExceptionBase(String mensagem) {
		super(mensagem);
	}
	
}
