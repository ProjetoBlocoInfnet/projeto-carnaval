package dao;

import java.util.ArrayList;
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
		Integrante integrante = new Integrante("","");
		if(entidade instanceof Integrante)
		{
			integrante = (Integrante) entidade;	
		}
		else
		{
			return false;
		}

		return (IntegranteDAO.integrantes.replace(integrante.getId(), integrante) != null);
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Integrante integrante = new Integrante("","");
		if(entidade instanceof Integrante)
		{
			integrante = (Integrante) entidade;	
		}
		else
		{
			return false;
		}

		return (IntegranteDAO.integrantes.remove(integrante.getId()) != null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> integrantes = new ArrayList<>();
		for(int i=0; i< IntegranteDAO.integrantes.size(); i++ )
		{
			integrantes.add(IntegranteDAO.integrantes.get(i));
		}
		return integrantes;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		return IntegranteDAO.integrantes.get( numero );
	}
}
