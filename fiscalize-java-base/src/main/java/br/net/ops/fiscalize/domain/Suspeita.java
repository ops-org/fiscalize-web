package br.net.ops.fiscalize.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@Entity
public class Suspeita {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose private Integer suspeitaId;

	@Expose private Boolean suspeita;
	@Expose private Boolean suspeitaValor;
	@Expose private Boolean suspeitaObjeto;
	@Expose private Boolean suspeitaBeneficiario;
	@Expose private String comentarios;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Expose private Date dataInclusao;

	@ManyToOne
	@JoinColumn(name="notaFiscalId")
	@Expose private NotaFiscal notaFiscal;
	
	@ManyToOne
	@JoinColumn(name="usuarioId")
	@Expose private Usuario usuario;

	public Integer getSuspeitaId() {
		return suspeitaId;
	}

	public void setSuspeitaId(Integer suspeitaId) {
		this.suspeitaId = suspeitaId;
	}

	public Boolean getSuspeita() {
		return suspeita;
	}

	public void setSuspeita(Boolean suspeita) {
		this.suspeita = suspeita;
	}

	public Boolean getSuspeitaValor() {
		return suspeitaValor;
	}

	public void setSuspeitaValor(Boolean suspeitaValor) {
		this.suspeitaValor = suspeitaValor;
	}

	public Boolean getSuspeitaObjeto() {
		return suspeitaObjeto;
	}

	public void setSuspeitaObjeto(Boolean suspeitaObjeto) {
		this.suspeitaObjeto = suspeitaObjeto;
	}

	public Boolean getSuspeitaBeneficiario() {
		return suspeitaBeneficiario;
	}

	public void setSuspeitaBeneficiario(Boolean suspeitaBeneficiario) {
		this.suspeitaBeneficiario = suspeitaBeneficiario;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
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
