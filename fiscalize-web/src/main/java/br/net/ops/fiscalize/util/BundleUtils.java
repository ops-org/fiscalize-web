package br.net.ops.fiscalize.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class BundleUtils {

	// CONSTANTES #################################################################

	// ARQUIVOS DE MENSAGEM
	public static final String GLOBAL_MESSAGES = "global-messages";
	public static final String EXCEPTION_MESSAGES = "exception-messages";
	
	// PATHS
	public final static String KEY_CAMINHO_PADRAO = "caminho.padrao";

	// URL
	public final static String KEY_URL_LOCALHOST = "url.localhost";
	public final static String KEY_URL_PORTA = "url.porta";
	public final static String KEY_URL_APLICACAO = "url.aplicacao";
	public final static String KEY_URL_CACHE = "url.cache";
	
	public final static Locale LOCALE_PADRAO = new Locale("pt", "BR");
	
	// METODOS ####################################################################
	public static String obterMensagem(String resourceFile, String key) {
		ResourceBundle resource = ResourceBundle.getBundle(resourceFile, obterLocalePadrao());
		return resource.getString(key);
	}
	
	public static String obterMensagem(String resourceFile, Locale locale, String key) {
		ResourceBundle resource = ResourceBundle.getBundle(resourceFile, locale);
		return resource.getString(key);
	}
	
	public static String obterMensagem(String resourceFile, String key, Object... variaveis) {
		ResourceBundle resource = ResourceBundle.getBundle(resourceFile);
		String mensagem = resource.getString(key);
		return MessageFormat.format(mensagem, variaveis);
	}
	
	public static String obterMensagem(String resourceFile, Locale locale, String key, Object... variaveis) {
		ResourceBundle resource = ResourceBundle.getBundle(resourceFile, locale);
		String mensagem = resource.getString(key);
		return MessageFormat.format(mensagem, variaveis);
	}
	
	public static Locale obterLocalePadrao() {
		ResourceBundle resourceBundleGlobal = ResourceBundle.getBundle(BundleUtils.GLOBAL_MESSAGES);
		String lingua = resourceBundleGlobal.getString("lingua.padrao");
		String dialeto = resourceBundleGlobal.getString("dialeto.padrao");
		return new Locale(lingua, dialeto);
	}
	
}