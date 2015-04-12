package br.net.ops.fiscalize.domain;

public class SingletonParametros {

	public static final String TIPO_DATABASE = "DB";
	public static final String TIPO_SCRIPT = "SCRIPT";
	
	private String tipo;
	private String caminhoXml;
	private String caminhoSql;
	private String caminhoLog;
	private String caminhoImagens;
	
	private static SingletonParametros singleton = new SingletonParametros();
	
	private SingletonParametros() {
		tipo = null;
		caminhoXml = null;
		caminhoSql = null;
		caminhoLog = null;
	}
	   
	public static SingletonParametros getInstance() {
		return singleton;
	}

	public String getCaminhoXml() {
		return caminhoXml;
	}

	public void setCaminhoXml(String caminhoXml) {
		this.caminhoXml = caminhoXml;
	}

	public String getCaminhoSql() {
		return caminhoSql;
	}

	public void setCaminhoSql(String caminhoSql) {
		this.caminhoSql = caminhoSql;
	}

	public String getCaminhoLog() {
		return caminhoLog;
	}

	public void setCaminhoLog(String caminhoLog) {
		this.caminhoLog = caminhoLog;
	}
	
	public String getCaminhoImagens() {
		return caminhoImagens;
	}

	public void setCaminhoImagens(String caminhoImagens) {
		this.caminhoImagens = caminhoImagens;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
