package br.net.ops.fiscalize.pojo.retorno;

import com.google.gson.annotations.Expose;

public class Erro extends Retorno {

	@Expose private String erro;

	public Erro(String erro) {
		super(true);
		this.erro = erro;
	}
	
	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
	
}
