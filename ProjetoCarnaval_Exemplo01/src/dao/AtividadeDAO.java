package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negocio.Atividade;
import negocio.Entidade;

public class AtividadeDAO extends AbstractDAO implements DAO
{
	private static Map< Integer, Atividade> atividades = new HashMap<>();
	
	@Override
	public boolean cadastrar(Entidade entidade)
	{
		Atividade atividade = new Atividade();
		if(entidade instanceof Atividade)
		{
			atividade = (Atividade) entidade;	
		}
		else
		{
			return false;
		}

		atividade.setId( AtividadeDAO.atividades.size() );

		if(AtividadeDAO.atividades.put( atividade.getId(), atividade ) != null)
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
		List<Entidade> atividades = new ArrayList<>();
		for(int i=0; i< AtividadeDAO.atividades.size(); i++ )
		{
			atividades.add(AtividadeDAO.atividades.get(i));
		}
		return atividades;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		return AtividadeDAO.atividades.get( numero );
	}

}
