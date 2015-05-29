package negocio;

import java.util.Date;

public class Atividade implements Entidade
{	
	private Integer id;
	private Integer id_integrante;
	private EscolaSamba escolaSamba;
	private Acao acao;
	private Date data_inicio = new Date();
	private Date data_fim = new Date();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acao == null) ? 0 : acao.hashCode());
		result = prime * result
				+ ((escolaSamba == null) ? 0 : escolaSamba.hashCode());
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
		Atividade other = (Atividade) obj;
		if (acao != other.acao)
			return false;
		if (escolaSamba == null) {
			if (other.escolaSamba != null)
				return false;
		} else if (!escolaSamba.equals(other.escolaSamba))
			return false;
		return true;
	}

	public EscolaSamba getEscolaSamba() {
		return escolaSamba;
	}
	public void setEscolaSamba(EscolaSamba escolaSamba) {
		this.escolaSamba = escolaSamba;
	}
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_integrante() {
		return id_integrante;
	}
	public void setId_integrante(Integer id_integrante) {
		this.id_integrante = id_integrante;
	}
	public Date getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}
	public Date getData_fim() {
		return data_fim;
	}
	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}
}
