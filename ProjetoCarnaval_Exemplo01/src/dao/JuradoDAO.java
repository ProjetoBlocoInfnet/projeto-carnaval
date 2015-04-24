package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negocio.Entidade;
import negocio.Jurado;

public class JuradoDAO extends AbstractDAO implements DAO
{
	private static Map<Integer, Jurado> jurados = new HashMap<>(); //TODO nï¿½o estamos usando banco ainda. Por enquanto isso fica aqui.

	{
		Jurado j = new Jurado("","");
		j.setNome("Ricardo");
		JuradoDAO.jurados.put(0, j);
		j = new Jurado("","");
		j.setNome("Lúcio");
		JuradoDAO.jurados.put(1, j);
	}
	
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
		Jurado jurado = new Jurado("","");
		if(entidade instanceof Jurado)
		{
			jurado = (Jurado) entidade;	
		}
		else
		{
			return false;
		}

		if (JuradoDAO.jurados.containsKey(jurado.getId())) {
			return JuradoDAO.jurados.put(jurado.getId(), jurado) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Jurado jurado = new Jurado("","");
		if(entidade instanceof Jurado)
		{
			jurado = (Jurado) entidade;	
		}
		else
		{
			return false;
		}

		return (JuradoDAO.jurados.remove(jurado.getId()) != null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> jurados = new ArrayList<>();
		for(int i=0; i< JuradoDAO.jurados.size(); i++ )
		{
			jurados.add(JuradoDAO.jurados.get(i));
		}
		return jurados;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		return JuradoDAO.jurados.get(numero);
	}

	@Override
	public Collection<Entidade> obterTodosCollection() {
		List<Entidade> jurados = new ArrayList<>();
		for(int i=0; i< JuradoDAO.jurados.size(); i++ )
		{
			jurados.add(JuradoDAO.jurados.get(i));
		}
		return jurados;
	}
}
