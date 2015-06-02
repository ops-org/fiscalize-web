package br.net.ops.fiscalize.exception;

import android.content.Context;
import android.util.Log;

import br.net.ops.fiscalize.R;

@SuppressWarnings("serial")
public class ResgatarSuspeitasException extends BaseException {

	private final static int KEY = R.string.exception_resgatar_suspeitas;
	private final static int LEVEL = Log.WARN;

	public ResgatarSuspeitasException(String tag, Context context) {
		super(context, tag, KEY, LEVEL);
	}

	public ResgatarSuspeitasException(String tag, Context context, Throwable cause) {
		super(context, tag, KEY, cause, LEVEL);
	}
	
}
