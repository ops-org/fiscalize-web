package br.net.ops.fiscalize.webutil.base;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.net.ops.fiscalize.webutil.BundleUtils;
import br.net.ops.fiscalize.webutil.Utilidade;

@SuppressWarnings("serial")
public abstract class ExceptionBase extends Exception {

	// ATRIBUTOS ##################################################################
	private Logger logger;
	private Level level = Level.INFO;
	
	private String key;
	private String localizedMessage;
	
	// CONSTRUTORES ###############################################################
	public ExceptionBase(String key, Object... variaveis) {
		super();
		this.logger = Utilidade.getLogger();
		this.key = key;
		this.logar(variaveis);
	}
	
	public ExceptionBase(String key, Throwable cause, Object... variaveis) {
		super(cause);
		this.logger = Utilidade.getLogger();
		this.key = key;
		this.logar(variaveis);
	}
	
	public ExceptionBase(Level level, String key, Object... variaveis) {
		super();
		this.logger = Utilidade.getLogger();
		this.level = level;
		this.key = key;
		this.logar(variaveis);
	}
	
	public ExceptionBase(Level level, String key, Throwable cause, Object... variaveis) {
		super(cause);
		this.logger = Utilidade.getLogger();
		this.level = level;
		this.key = key;
		this.logar(variaveis);
	}
	
	// M�TODOS ####################################################################
	public void logar(Object... variaveis) {
		this.localizedMessage = BundleUtils.obterMensagem(BundleUtils.EXCEPTION_MESSAGES, this.key, variaveis);
		logger.log(this.level, this.localizedMessage + ((this.getCause())!=null?"\n"+this.getCause():""));
	}

	
	// GETS E SETS ################################################################

	// Sobrescrevendo metodos da classe pai para usar atributo "localizedMessage".
	public String getMessage() {
		return localizedMessage;
	}

	public void setMessage(String message) {
		this.localizedMessage = message;
	}
	
	
	
}
