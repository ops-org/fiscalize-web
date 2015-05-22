package br.net.ops.fiscalize.pojo;

import javax.persistence.Entity;

import com.google.gson.annotations.Expose;

@Entity
public class Mensagem extends Retorno {

	@Expose private String mensagem;

	public Mensagem(String mensagem) {
		super(false);
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
