package br.net.ops.fiscalize.exception;

import android.content.Context;
import android.util.Log;

import br.net.ops.fiscalize.R;

@SuppressWarnings("serial")
public class UsuarioResgatarException extends BaseException {

	private final static int KEY = R.string.exception_usuario_resgatar;
	private final static int LEVEL = Log.WARN;
	
	public UsuarioResgatarException(String tag, Context context) {
		super(context, tag, KEY, LEVEL);
	}

	public UsuarioResgatarException(String tag, Context context, Throwable cause) {
		super(context, tag, KEY, cause, LEVEL);
	}
	
}
