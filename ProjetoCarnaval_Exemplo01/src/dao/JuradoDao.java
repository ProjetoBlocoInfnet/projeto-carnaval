package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negocio.Entidade;
import negocio.Jurado;

public class JuradoDAO extends AbstractDAO implements DAO
{
	private static Map<Integer, Jurado> jurados = new HashMap<>(); //TODO n�o estamos usando banco ainda. Por enquanto isso fica aqui.

	public Jurado obterPorNome(String nome)
	{
		for(int i=0; i<JuradoDAO.jurados.size(); i++)
		{
			if(JuradoDAO.jurados.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				return JuradoDAO.jurados.get(i);
			}
		}
		return null;
	}

	@Override
	public boolean cadastrar(Entidade entidade) {
		Jurado jurado = new Jurado("","");
		if(entidade instanceof Jurado)
		{
			jurado = (Jurado) entidade;	
		}
		else
		{
			return false;
		}

		jurado.setId( JuradoDAO.jurados.size() );

		if(JuradoDAO.jurados.put( jurado.getId(), jurado ) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean alterar(Entidade entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Entidade> obterTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		return JuradoDAO.jurados.get(numero);
	}
}
