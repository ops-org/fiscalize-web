package br.net.ops.fiscalize.exception;

import br.net.ops.fiscalize.webutil.base.ExceptionBase;

@SuppressWarnings("serial")
public class UsuarioNaoAutorizadoException extends ExceptionBase {

	private final static String KEY = "erro.rest.nao.autorizado";
	
	public UsuarioNaoAutorizadoException() {
		super(KEY);
	}

	public UsuarioNaoAutorizadoException(Throwable cause) {
		super(KEY, cause);
	}
	
}
