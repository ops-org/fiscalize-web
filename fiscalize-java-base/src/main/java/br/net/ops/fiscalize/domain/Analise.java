package br.net.ops.fiscalize.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;

@Entity
public class Analise {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose private Integer analiseId;
	@Expose private Boolean concluida;
	@Expose private String comentarios;

	@ManyToOne
	@JoinColumn(name="notaFiscalId")
	@Expose private NotaFiscal notaFiscal;
	
	@ManyToOne
	@JoinColumn(name="responsavelUsuarioId")
	@Expose private Usuario usuario;

	public Integer getAnaliseId() {
		return analiseId;
	}

	public void setAnaliseId(Integer analiseId) {
		this.analiseId = analiseId;
	}

	public Boolean getConcluida() {
		return concluida;
	}

	public void setConcluida(Boolean concluida) {
		this.concluida = concluida;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
