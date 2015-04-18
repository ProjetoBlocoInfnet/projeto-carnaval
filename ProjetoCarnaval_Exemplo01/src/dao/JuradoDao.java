package dao;

import java.util.HashSet;
import java.util.Set;

import negocio.Jurado;

public class JuradoDao
{
	private Set<Jurado> jurados = new HashSet<Jurado>(); //TODO não estamos usando banco ainda. Por enquanto isso fica aqui.

	public boolean inserir(Jurado jurado)
	{
		return this.jurados.add(jurado); // ADD retorna boolean então satisfaz a checagem
	}

	public boolean alterar(Jurado quesito)
	{
		return true;
	}

	public Jurado obterPorNome(String nome)
	{
		for(Jurado j : jurados)
		{
			if(j.getNome().matches("(.*)" + nome + "(.*)"))
			{
				return j;
			}
		}
		return null;
	}
}
