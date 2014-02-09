package br.com.cotasparlamentares.normalizacotas.domain;

public class SingletonParametros {

	private String caminhoXml;
	private String caminhoSql;
	private String caminhoLog;
	private Boolean inserirCreateTable;
	
	private static SingletonParametros singleton = new SingletonParametros();
	
	private SingletonParametros() { 
		caminhoXml = null;
		caminhoSql = null;
		caminhoLog = null;
		inserirCreateTable = true;
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

	public Boolean getInserirCreateTable() {
		return inserirCreateTable;
	}

	public void setInserirCreateTable(Boolean inserirCreateTable) {
		this.inserirCreateTable = inserirCreateTable;
	}

}
