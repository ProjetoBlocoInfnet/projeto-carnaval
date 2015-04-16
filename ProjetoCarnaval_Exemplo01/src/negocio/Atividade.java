package negocio;

import enumerator.Acao;

public class Atividade
{
	private EscolaSamba escolaSamba;
	private Acao acao;

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
}