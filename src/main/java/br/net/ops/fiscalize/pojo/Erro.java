package br.net.ops.fiscalize.pojo;

import javax.persistence.Entity;

import com.google.gson.annotations.Expose;

@Entity
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
