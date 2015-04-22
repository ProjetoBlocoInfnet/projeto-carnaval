package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negocio.Entidade;
import negocio.Integrante;

public class IntegranteDAO extends AbstractDAO implements DAO
{
	private static Map< Integer, Integrante> integrantes = new HashMap<>();
	
	@Override
	public boolean cadastrar(Entidade entidade) {
		Integrante integrante = new Integrante("","");
		if(entidade instanceof Integrante)
		{
			integrante = (Integrante) entidade;	
		}
		else
		{
			return false;
		}

		integrante.setId( IntegranteDAO.integrantes.size() );

		if(IntegranteDAO.integrantes.put( integrante.getId(), integrante ) != null)
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
		return IntegranteDAO.integrantes.get( numero );
	}
}
