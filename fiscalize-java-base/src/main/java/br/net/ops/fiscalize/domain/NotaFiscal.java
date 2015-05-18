package br.net.ops.fiscalize.domain;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.net.ops.fiscalize.exception.AnoException;
import br.net.ops.fiscalize.exception.MesException;
import br.net.ops.fiscalize.exception.ParcelaException;
import br.net.ops.fiscalize.exception.TipoDocumentoException;
import br.net.ops.fiscalize.exception.ValorException;
import br.net.ops.fiscalize.exception.ValorGlosaException;
import br.net.ops.fiscalize.exception.ValorLiquidoException;
import br.net.ops.fiscalize.util.Utilidade;

import com.google.gson.annotations.Expose;

@Entity
public class NotaFiscal {

	public static int TIPO_DOCUMENTO_NOTA_FISCAL = 0;
	public static int TIPO_DOCUMENTO_RECIBO = 1;
	public static int TIPO_DOCUMENTO_DESPESA_EXTERIOR = 2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose private Integer notaFiscalId;
	
	@Expose private String descricao;
	@Expose private String descricaoSubCota;
	@Expose private String fornecedor;
	@Expose private String cpfCnpj;
	@Expose private Integer ano;
	@Expose private Integer mes;
	
	@Expose private String numeroDocumento;
	@Expose private Integer parcela;
	@Expose private Integer tipoDocumentoFiscal;
	
	@Expose private String nomePassageiro;
	@Expose private String trechoViagem;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Expose private Date dataEmissao;
	
	@Column(length = 10, precision = 2)
	@Expose private BigDecimal valor;
	@Column(length = 10, precision = 2)
	@Expose private BigDecimal valorGlosa;
	@Column(length = 10, precision = 2)
	@Expose private BigDecimal valorLiquido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Expose public Date dataInclusao;
	
	@ManyToOne
	@JoinColumn(name="parlamentarId")
	@Expose private Parlamentar parlamentar;
	
	@ManyToOne
	@JoinColumn(name="cotaId")
	@Expose private Cota cota;
	
	@ManyToOne
	@JoinColumn(name="ufId")
	@Expose private Uf uf;
	
	@OneToMany(mappedBy="notaFiscal", fetch = FetchType.LAZY)
	private List<Suspeita> suspeitas = null;
	
	@OneToMany(mappedBy="notaFiscal", fetch = FetchType.LAZY)
	private List<Analise> analises = null;

	public static Date retornarDataEmissao(String data) {
		Date retorno;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try {
			retorno = sdf.parse(data);
		} catch(ParseException | NullPointerException e) {
			retorno = null;
		}
		return retorno;
	}
	
	public static Integer retornarTipoDocumento(String tipoDocumento) throws TipoDocumentoException {
		Integer retorno;
		try {
			retorno = Integer.parseInt(tipoDocumento);
			if(retorno!=TIPO_DOCUMENTO_DESPESA_EXTERIOR && retorno!=TIPO_DOCUMENTO_NOTA_FISCAL && retorno!=TIPO_DOCUMENTO_RECIBO) {
				Logger logger  = Utilidade.getLogger();
				logger.log(Level.WARNING, "Novo Tipo de Documento: " + retorno);
			}
		} catch(NumberFormatException e) {
			throw new TipoDocumentoException(e);
		}
		return retorno;
	}
	
	public static Integer retornarAno(String ano) throws AnoException {
		Integer retorno;
		try {
			retorno = Integer.parseInt(ano);
		} catch(NumberFormatException e) {
			throw new AnoException(e);
		}
		return retorno;
	}
	
	public static Integer retornarMes(String mes) throws MesException {
		Integer retorno;
		try {
			retorno = Integer.parseInt(mes);
		} catch(NumberFormatException e) {
			throw new MesException(e);
		}
		return retorno;
	}
	
	public static Integer retornarParcela(String parcela) throws ParcelaException {
		Integer retorno;
		try {
			retorno = Integer.parseInt(parcela);
		} catch(NumberFormatException e) {
			throw new ParcelaException(e);
		}
		return retorno;
	}
	
	public static BigDecimal retornarValor(String valor) throws ValorException {
		BigDecimal retorno;
		try {
			retorno = BigDecimal.valueOf(Double.valueOf(valor));
		} catch(NumberFormatException e) {
			throw new ValorException(e);
		}
		return retorno;
	}
	
	public static BigDecimal retornarValorGlosa(String valor) throws ValorGlosaException {
		BigDecimal retorno;
		try {
			retorno = BigDecimal.valueOf(Double.valueOf(valor));
		} catch(NumberFormatException e) {
			throw new ValorGlosaException(e);
		}
		return retorno;
	}
	
	public static BigDecimal retornarValorLiquido(String valor) throws ValorLiquidoException {
		BigDecimal retorno;
		try {
			retorno = BigDecimal.valueOf(Double.valueOf(valor));
		} catch(NumberFormatException e) {
			throw new ValorLiquidoException(e);
		}
		return retorno;
	}
	
	public Integer getNotaFiscalId() {
		return notaFiscalId;
	}

	public void setNotaFiscalId(Integer notaFiscalId) {
		this.notaFiscalId = notaFiscalId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoSubCota() {
		return descricaoSubCota;
	}

	public void setDescricaoSubCota(String descricaoSubCota) {
		this.descricaoSubCota = descricaoSubCota;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Integer getParcela() {
		return parcela;
	}

	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}

	public Integer getTipoDocumentoFiscal() {
		return tipoDocumentoFiscal;
	}

	public void setTipoDocumentoFiscal(Integer tipoDocumentoFiscal) {
		this.tipoDocumentoFiscal = tipoDocumentoFiscal;
	}

	public String getNomePassageiro() {
		return nomePassageiro;
	}

	public void setNomePassageiro(String nomePassageiro) {
		this.nomePassageiro = nomePassageiro;
	}

	public String getTrechoViagem() {
		return trechoViagem;
	}

	public void setTrechoViagem(String trechoViagem) {
		this.trechoViagem = trechoViagem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorGlosa() {
		return valorGlosa;
	}

	public void setValorGlosa(BigDecimal valorGlosa) {
		this.valorGlosa = valorGlosa;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Parlamentar getParlamentar() {
		return parlamentar;
	}

	public void setParlamentar(Parlamentar parlamentar) {
		this.parlamentar = parlamentar;
	}

	public Cota getCota() {
		return cota;
	}

	public void setCota(Cota cota) {
		this.cota = cota;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
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
