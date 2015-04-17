package dao;

import java.util.HashSet;
import java.util.Set;

import negocio.Quesito;

public class QuesitoDao
{
	private Set<Quesito> quesitos = new HashSet<Quesito>(); //TODO não estamos usando banco ainda. Por enquanto isso fica aqui.

	public boolean inserir(Quesito quesito)
	{
		return this.quesitos.add(quesito); // ADD retorna boolean então satisfaz a checagem
	}

	public boolean alterar(Quesito quesito)
	{
		return true;
	}

	public Quesito obterPorNome(String nome)
	{
		for(Quesito q : quesitos)
		{
			if(q.getNome().matches("(.*)" + nome + "(.*)"))
			{
				return q;
			}
		}
		return null;
	}
}
