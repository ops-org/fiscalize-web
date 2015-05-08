package br.net.ops.fiscalize.util.base;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.net.ops.fiscalize.util.BundleUtils;
import br.net.ops.fiscalize.util.Utilidade;

public abstract class ControllerBase {

	// CONSTANTES #################################################################
	public static final Level LEVEL_PADRAO = Level.INFO;
	
	// ATRIBUTOS ##################################################################
	protected Logger logger;
	
	// CONSTRUTOR #################################################################
	public ControllerBase() {
		this.logger = Utilidade.getLogger();
	}
	
	// RESOURCE BUNDLE ############################################################	
	protected String obterMensagemActionResource(String key) {
		return BundleUtils.obterMensagem(BundleUtils.GLOBAL_MESSAGES, key);
	}

}
