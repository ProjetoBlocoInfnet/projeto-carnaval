package negocio;

import java.util.HashSet;
import java.util.Set;

public class Integrante extends Pessoa
{
	private Set<Atividade> atividades = new HashSet<>();
	
	private Set<EscolaSamba> escolaSamba = new HashSet<>();
	
	public Integrante( String login, String senha) {
		super( login, senha );
	}


	public Set<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(Set<Atividade> atividades) {
		this.atividades = atividades;
	}
	
	public Set<EscolaSamba> getEscolaSamba() {
		return escolaSamba;
	}

	public void setEscolaSamba(Set<EscolaSamba> escolaSamba) {
		this.escolaSamba = escolaSamba;
	}
	
	

}
