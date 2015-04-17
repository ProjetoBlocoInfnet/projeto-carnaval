package negocio;

import java.util.HashSet;
import java.util.Set;

public class Integrante extends Pessoa
{
	public final Set<Atividade> atividades = new HashSet<>();
	
	public Integrante( String login, String senha) {
		super( login, senha );
	}
}
