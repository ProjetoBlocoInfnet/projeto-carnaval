package negocio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import enumerator.Grupos;

public class EscolaSamba extends Usuario
{
	public EscolaSamba(String login, String senha) {
		super(login, senha);	
	}

	private final Set<String> cor = new HashSet<>();
	
	private String nome;
	private String enderecoQuadra;
	private String enderecoBarracao;
	private Date dataFundacao;
	private String lema;
	private String filiacao;
	private Grupos grupoAtual;
	private String email;
	private String telefone;
	private String cnpj;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
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
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
		
		this.cor.clear();
		this.cor.addAll(cor);
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
}
