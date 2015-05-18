package br.net.ops.fiscalize.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose private Integer usuarioId;
	private String usuario;
	private String senha;
	private String facebookId;
	@Expose private String tokenId;
	private String nome;
	private String email;
	private Boolean experiente;
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.LAZY)
	private List<Suspeita> suspeitas = null;
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.LAZY)
	private List<Analise> analises = null;

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getExperiente() {
		return experiente;
	}

	public void setExperiente(Boolean experiente) {
		this.experiente = experiente;
	}

	public List<Suspeita> getSuspeitas() {
		return suspeitas;
	}

	public void setSuspeitas(List<Suspeita> suspeitas) {
		this.suspeitas = suspeitas;
	}

	public List<Analise> getAnalises() {
		return analises;
	}

	public void setAnalises(List<Analise> analises) {
		this.analises = analises;
	}
	
}
