package negocio;

import java.util.HashSet;
import java.util.Set;

public class Integrante extends Pessoa
{
	private Set<Atividade> atividades = new HashSet<>();
	
	private EscolaSamba escolaSamba;
	
	public Integrante( String login, String senha) {
		super( login, senha );
	}


	public Set<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(Set<Atividade> atividades) {
		this.atividades = atividades;
	}

	
	public EscolaSamba getEscolaSamba() {
		return escolaSamba;
	}

	public void setEscolaSamba(EscolaSamba escolaSamba) {
		this.escolaSamba = escolaSamba;
	}

}
