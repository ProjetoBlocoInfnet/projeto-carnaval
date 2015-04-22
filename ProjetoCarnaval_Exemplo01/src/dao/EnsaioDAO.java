package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negocio.Ensaio;
import negocio.Entidade;
import negocio.EscolaSamba;

public class EnsaioDAO extends AbstractDAO implements DAO
{
	private static Map< Integer, Ensaio > ensaios = new HashMap<>();

	//Dados de teste. As variáveis estão privadas então não temos risco de acesso externo.
	private EscolaSamba samba;
	private Ensaio ensaio;
	{
	this.samba = (EscolaSamba) new EscolaSambaDAO().obterPorId( 0 );
	this.ensaio = new Ensaio( this.samba, new Date());
	this.cadastrar( this.ensaio );
	}

	@Override
	public boolean cadastrar(Entidade entidade)
	{
		Ensaio ensaio = new Ensaio();
		if(entidade instanceof Ensaio)
		{
			ensaio = (Ensaio) entidade;	
		}
		else
		{
			return false;
		}

		ensaio.setId( EnsaioDAO.ensaios.size() );

		if(EnsaioDAO.ensaios.put( ensaio.getId(), ensaio ) != null)
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
		List<Entidade> ensaios = new ArrayList<>();
		for(int i=0; i< EnsaioDAO.ensaios.size(); i++ )
		{
			ensaios.add(EnsaioDAO.ensaios.get(i));
		}
		return ensaios;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		return EnsaioDAO.ensaios.get(numero);
	}
}
