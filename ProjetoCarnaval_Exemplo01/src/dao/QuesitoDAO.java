package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negocio.Entidade;
import negocio.Quesito;

public class QuesitoDAO extends AbstractDAO implements DAO
{
	private static Map<Integer, Quesito> quesitos = new HashMap<>(); //TODO não estamos usando banco ainda. Por enquanto isso fica aqui.

	public Quesito obterPorNome(String nome)
	{
		for(int i=0; i<QuesitoDAO.quesitos.size(); i++)
		{
			if(QuesitoDAO.quesitos.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				return QuesitoDAO.quesitos.get(i);
			}
		}
		return null;
	}

	@Override
	public boolean cadastrar(Entidade entidade) {
		Quesito quesito = new Quesito();
		if(entidade instanceof Quesito)
		{
			quesito = (Quesito) entidade;	
		}
		else
		{
			return false;
		}

		quesito.setId( QuesitoDAO.quesitos.size() );

		if(QuesitoDAO.quesitos.put( quesito.getId(), quesito ) != null)
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
		Quesito quesito = new Quesito();
		if(entidade instanceof Quesito)
		{
			quesito = (Quesito) entidade;	
		}
		else
		{
			return false;
		}

		return (QuesitoDAO.quesitos.replace(quesito.getId(), quesito) != null);
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Quesito quesito = new Quesito();
		if(entidade instanceof Quesito)
		{
			quesito = (Quesito) entidade;	
		}
		else
		{
			return false;
		}

		return (QuesitoDAO.quesitos.remove(quesito.getId()) != null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> quesito = new ArrayList<>();
		for(int i=0; i< QuesitoDAO.quesitos.size(); i++ )
		{
			quesito.add(QuesitoDAO.quesitos.get(i));
		}
		return quesito;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		return QuesitoDAO.quesitos.get(numero);
	}
}
