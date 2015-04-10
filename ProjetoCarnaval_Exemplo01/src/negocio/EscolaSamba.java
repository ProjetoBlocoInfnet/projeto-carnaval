package negocio;

import java.util.Date;
import java.util.Set;

import enumerator.Grupos;

public class EscolaSamba
{
	private String nome;
	private String enderecoQuadra;
	private String enderecoBarracao;
	private Set<String> cor;
	private Date dataFundacao;
	private String lema;
	private String filiacao;
	private Grupos grupoAtual;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataFundacao == null) ? 0 : dataFundacao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EscolaSamba other = (EscolaSamba) obj;
		if (dataFundacao == null) {
			if (other.dataFundacao != null)
				return false;
		} else if (!dataFundacao.equals(other.dataFundacao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEnderecoQuadra() {
		return enderecoQuadra;
	}
	public void setEnderecoQuadra(String enderecoQuadra) {
		this.enderecoQuadra = enderecoQuadra;
	}
	public String getEnderecoBarracao() {
		return enderecoBarracao;
	}
	public void setEnderecoBarracao(String enderecoBarracao) {
		this.enderecoBarracao = enderecoBarracao;
	}
	public Set<String> getCor() {
		return cor;
	}
	public void setCor(Set<String> cor) {
		this.cor = cor;
	}
	public Date getDataFundacao() {
		return dataFundacao;
	}
	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	public String getLema() {
		return lema;
	}
	public void setLema(String lema) {
		this.lema = lema;
	}
	public String getFiliacao() {
		return filiacao;
	}
	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}
	public Grupos getGrupoAtual() {
		return grupoAtual;
	}
	public void setGrupoAtual(Grupos grupoAtual) {
		this.grupoAtual = grupoAtual;
	}
}
