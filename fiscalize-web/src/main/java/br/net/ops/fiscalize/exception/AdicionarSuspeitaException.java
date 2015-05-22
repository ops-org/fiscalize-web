package br.net.ops.fiscalize.exception;

import br.net.ops.fiscalize.webutil.base.ExceptionBase;

@SuppressWarnings("serial")
public class AdicionarSuspeitaException extends ExceptionBase {

	private final static String KEY = "erro.rest.adicionar.suspeita";
	
	public AdicionarSuspeitaException() {
		super(KEY);
	}

	public AdicionarSuspeitaException(Throwable cause) {
		super(KEY, cause);
	}
	
}
