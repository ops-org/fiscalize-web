package br.net.ops.fiscalize.util.base;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.net.ops.fiscalize.util.Utilidade;

public abstract class ServiceBase {
	
	// CONSTANTES #################################################################
	public final Level LEVEL_PADRAO = Level.INFO;
	
	// ATRIBUTOS ##################################################################
	protected Logger logger = Utilidade.getLogger();

}
