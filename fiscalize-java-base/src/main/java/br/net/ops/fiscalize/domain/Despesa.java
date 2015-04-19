package br.net.ops.fiscalize.domain;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.format.annotation.DateTimeFormat;

import br.net.ops.fiscalize.exception.DespesaReflectionException;

@NamedQueries({
	@NamedQuery(
	name = "findAllDespesas",
	query = "FROM Despesa despesa ORDER BY despesa.despesaId"
	)
})

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"ideCadastro", "nuCarteiraParlamentar", "nuLegislatura", 
		"numAno", "numMes", "numLote", "txtCNPJCPF", "codLegislatura", "datEmissao", "numParcela", 
		"txtNumero", "txtPassageiro", "vlrLiquido", "numRessarcimento", "vlrDocumento"}))
public class Despesa {

	private static final String TABELA_DESPESA = "Despesa";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer despesaId;

	public String txNomeParlamentar;
	public String ideCadastro;
	public String nuCarteiraParlamentar;
	public String nuLegislatura;
	public String sgUF;
	public String sgPartido;
	public String codLegislatura;
	public String numSubCota;
	public String txtDescricao;
	public String numEspecificacaoSubCota;
	public String txtDescricaoEspecificacao;
	public String txtFornecedor;
	public String txtCNPJCPF;
	public String txtNumero;
	public String indTipoDocumento;
	public String datEmissao;
	public String vlrDocumento;
	public String vlrGlosa;
	public String vlrLiquido;
	public String numMes;
	public String numAno;
	public String numParcela;
	public String txtPassageiro;
	public String txtTrecho;
	public String numLote;
	public String numRessarcimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date dataInclusao;
	
	public String retornarCreateSQL() throws DespesaReflectionException {
		ArrayList<String> listaCampos = retornarCampos();
		
		StringBuilder sb = new StringBuilder();
		for (String campo: listaCampos) {
			sb.append(campo + " VARCHAR(500), ");
		}
		
		String campos = sb.toString();
		if(campos.length()>2) campos = campos.substring(0, campos.length()-2); // retirando  ultima  virgula e espaco 
		
		return "CREATE TABLE " + TABELA_DESPESA + " (despesaId INT PRIMARY KEY, " + campos + ");";
	}
	
	public String retornarInsertSQL() throws DespesaReflectionException {
		return "INSERT INTO " + TABELA_DESPESA + " (" + retornarCamposInsert() + ") VALUES (" + retornarValoresInsert() + ");";
	}

	// Retorna NOME dos campos para o insert
	private String retornarCamposInsert() {
		ArrayList<String> listaCampos = retornarCampos();
		
		StringBuilder sb = new StringBuilder();
		for (String campo: listaCampos) {
			sb.append(campo + ", ");
		}
		
		String retorno = sb.toString();
		if(retorno.length()>2) retorno = retorno.substring(0, retorno.length()-2); // retirando último ", "
		
		return retorno;
	}
	
	// Retorna VALOR dos campos para o insert
	private String retornarValoresInsert() throws DespesaReflectionException {
		ArrayList<String> listaCampos = retornarCampos();
		
		StringBuilder sb = new StringBuilder();
		for (String campo: listaCampos) {
			
			try {
				String valor = BeanUtils.getProperty(this, campo);
				sb.append("'" + valor.replaceAll("'", "''").replaceAll("\"", "\"\"") + "', "); // Replace dos caracteres ' e "
			} catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
				throw new DespesaReflectionException(e);
			}
		}
		
		String retorno = sb.toString();
		if(retorno.length()>0) retorno = retorno.substring(0, retorno.length()-2);
		
		return retorno; 
	}
	
	// Usa reflection para retornar os campos da classe
	private ArrayList<String> retornarCampos() {
		Class<?> classe = Despesa.class;
		
		Field[] campos = classe.getFields();
		ArrayList<String> listaCampos = new ArrayList<String>();
		for (int i = 0; i < campos.length; i++) {
			listaCampos.add(campos[i].getName());
		}
		Collections.sort(listaCampos);
		
		return listaCampos;
	}
	
	public String toString() {
		return txNomeParlamentar + "(" + sgPartido + "/" + sgUF + ")";
	}
	
	public String getTxNomeParlamentar() {
		return txNomeParlamentar;
	}
	public void setTxNomeParlamentar(String txNomeParlamentar) {
		this.txNomeParlamentar = txNomeParlamentar;
	}
	public String getIdeCadastro() {
		return ideCadastro;
	}
	public void setIdeCadastro(String ideCadastro) {
		this.ideCadastro = ideCadastro;
	}
	public String getNuCarteiraParlamentar() {
		return nuCarteiraParlamentar;
	}
	public void setNuCarteiraParlamentar(String nuCarteiraParlamentar) {
		this.nuCarteiraParlamentar = nuCarteiraParlamentar;
	}
	public String getNuLegislatura() {
		return nuLegislatura;
	}
	public void setNuLegislatura(String nuLegislatura) {
		this.nuLegislatura = nuLegislatura;
	}
	public String getSgUF() {
		return sgUF;
	}
	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}
	public String getSgPartido() {
		return sgPartido;
	}
	public void setSgPartido(String sgPartido) {
		this.sgPartido = sgPartido;
	}
	public String getCodLegislatura() {
		return codLegislatura;
	}
	public void setCodLegislatura(String codLegislatura) {
		this.codLegislatura = codLegislatura;
	}
	public String getNumSubCota() {
		return numSubCota;
	}
	public void setNumSubCota(String numSubCota) {
		this.numSubCota = numSubCota;
	}
	public String getTxtDescricao() {
		return txtDescricao;
	}
	public void setTxtDescricao(String txtDescricao) {
		this.txtDescricao = txtDescricao;
	}
	public String getNumEspecificacaoSubCota() {
		return numEspecificacaoSubCota;
	}
	public void setNumEspecificacaoSubCota(String numEspecificacaoSubCota) {
		this.numEspecificacaoSubCota = numEspecificacaoSubCota;
	}
	public String getTxtDescricaoEspecificacao() {
		return txtDescricaoEspecificacao;
	}
	public void setTxtDescricaoEspecificacao(String txtDescricaoEspecificacao) {
		this.txtDescricaoEspecificacao = txtDescricaoEspecificacao;
	}
	public String getTxtFornecedor() {
		return txtFornecedor;
	}
	public void setTxtFornecedor(String txtFornecedor) {
		this.txtFornecedor = txtFornecedor;
	}
	public String getTxtCNPJCPF() {
		return txtCNPJCPF;
	}
	public void setTxtCNPJCPF(String txtCNPJCPF) {
		this.txtCNPJCPF = txtCNPJCPF;
	}
	public String getTxtNumero() {
		return txtNumero;
	}
	public void setTxtNumero(String txtNumero) {
		this.txtNumero = txtNumero;
	}
	public String getIndTipoDocumento() {
		return indTipoDocumento;
	}
	public void setIndTipoDocumento(String indTipoDocumento) {
		this.indTipoDocumento = indTipoDocumento;
	}
	public String getDatEmissao() {
		return datEmissao;
	}
	public void setDatEmissao(String datEmissao) {
		this.datEmissao = datEmissao;
	}
	public String getVlrDocumento() {
		return vlrDocumento;
	}
	public void setVlrDocumento(String vlrDocumento) {
		this.vlrDocumento = vlrDocumento;
	}
	public String getVlrGlosa() {
		return vlrGlosa;
	}
	public void setVlrGlosa(String vlrGlosa) {
		this.vlrGlosa = vlrGlosa;
	}
	public String getVlrLiquido() {
		return vlrLiquido;
	}
	public void setVlrLiquido(String vlrLiquido) {
		this.vlrLiquido = vlrLiquido;
	}
	public String getNumMes() {
		return numMes;
	}
	public void setNumMes(String numMes) {
		this.numMes = numMes;
	}
	public String getNumAno() {
		return numAno;
	}
	public void setNumAno(String numAno) {
		this.numAno = numAno;
	}
	public String getNumParcela() {
		return numParcela;
	}
	public void setNumParcela(String numParcela) {
		this.numParcela = numParcela;
	}
	public String getTxtPassageiro() {
		return txtPassageiro;
	}
	public void setTxtPassageiro(String txtPassageiro) {
		this.txtPassageiro = txtPassageiro;
	}
	public String getTxtTrecho() {
		return txtTrecho;
	}
	public void setTxtTrecho(String txtTrecho) {
		this.txtTrecho = txtTrecho;
	}
	public String getNumLote() {
		return numLote;
	}
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	public String getNumRessarcimento() {
		return numRessarcimento;
	}
	public void setNumRessarcimento(String numRessarcimento) {
		this.numRessarcimento = numRessarcimento;
	}
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	
}
