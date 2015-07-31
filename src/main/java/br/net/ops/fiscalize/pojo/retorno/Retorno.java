package br.net.ops.fiscalize.pojo.retorno;

public class Retorno {

	private boolean erro;

	public Retorno(boolean erro) {
		this.erro = erro;
	}
	
	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}
	
}