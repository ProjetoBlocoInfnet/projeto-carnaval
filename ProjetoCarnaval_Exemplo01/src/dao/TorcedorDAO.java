package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negocio.Entidade;
import negocio.EscolaSamba;
import negocio.Torcedor;

public class TorcedorDAO extends AbstractDAO implements DAO
{
	private static Map<Integer, Torcedor> torcedores = new HashMap<>();
	
	{
		Torcedor joaozinho = new Torcedor("joaozinho", "senha123");
		EscolaSamba escola = (EscolaSamba) new EscolaSambaDAO().obterPorId(0);
		joaozinho.setNome("Joaozinho das couves");
		joaozinho.setEscolaSamba( escola );
		cadastrar(joaozinho);
	}

	@Override
	public boolean cadastrar(Entidade entidade) {
		Torcedor torcedor = new Torcedor("","");
		if(entidade instanceof Torcedor)
		{
			torcedor = (Torcedor) entidade;	
		}
		else
		{
			return false;
		}

		torcedor.setId( TorcedorDAO.torcedores.size() );

		if(TorcedorDAO.torcedores.put( torcedor.getId(), torcedor ) != null)
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
		Torcedor torcedor = new Torcedor("","");
		if(entidade instanceof Torcedor)
		{
			torcedor = (Torcedor) entidade;	
		}
		else
		{
			return false;
		}

		return (TorcedorDAO.torcedores.replace(torcedor.getId(), torcedor) != null);
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Torcedor torcedor = new Torcedor("","");
		if(entidade instanceof Torcedor)
		{
			torcedor = (Torcedor) entidade;	
		}
		else
		{
			return false;
		}

		return (TorcedorDAO.torcedores.remove(torcedor.getId()) != null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> torcedor = new ArrayList<>();
		for(int i=0; i< TorcedorDAO.torcedores.size(); i++ )
		{
			torcedor.add(TorcedorDAO.torcedores.get(i));
		}
		return torcedor;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		return TorcedorDAO.torcedores.get(numero);
	}
	
	public Torcedor obterPorNome(String nome)
	{
		for(int i=0; i<TorcedorDAO.torcedores.size(); i++)
		{
			if(TorcedorDAO.torcedores.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				return TorcedorDAO.torcedores.get(i);
			}
		}
		return null;
	}

}
