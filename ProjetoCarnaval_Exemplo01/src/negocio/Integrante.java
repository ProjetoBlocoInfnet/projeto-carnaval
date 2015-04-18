package negocio;

import java.util.Set;

public class Integrante extends Pessoa
{
	public Integrante(String login, String senha) {
		super(login, senha);
		// TODO Auto-generated constructor stub
	}

	Set<Atividade> atividades;
}
