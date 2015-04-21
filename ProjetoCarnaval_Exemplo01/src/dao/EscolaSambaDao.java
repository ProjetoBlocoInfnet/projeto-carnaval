package dao;

import java.util.HashSet;
import java.util.Set;

import negocio.EscolaSamba;

public class EscolaSambaDao
{
	private Set<EscolaSamba> escolasSamba = new HashSet<EscolaSamba>(); //TODO não estamos usando banco ainda. Por enquanto isso fica aqui.

	public boolean inserir(EscolaSamba escolaSamba)
	{
		return this.escolasSamba.add(escolaSamba); // ADD retorna boolean então satisfaz a checagem
	}

	public boolean alterar(EscolaSamba quesito)
	{
		return true;
	}

	public EscolaSamba obterPorNome(String nome)
	{
		for(EscolaSamba e : escolasSamba)
		{
			if(e.getNome().matches("(.*)" + nome + "(.*)"))
			{
				return e;
			}
		}
		return null;
	}
}
