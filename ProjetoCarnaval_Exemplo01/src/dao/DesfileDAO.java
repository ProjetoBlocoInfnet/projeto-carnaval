package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negocio.Desfile;
import negocio.Entidade;

public class DesfileDAO extends AbstractDAO implements DAO {

	private static Map<Integer,Desfile> desfiles = new HashMap<>();
	
	{
		Desfile d = new Desfile();
		
	}
	
	@Override
	public boolean cadastrar(Entidade entidade) {
		Desfile desfile = new Desfile();
		if(entidade instanceof Desfile)
		{
			desfile = (Desfile) entidade;	
		}
		else
		{
			return false;
		}

		desfile.setId( DesfileDAO.desfiles.size() );
		
		DesfileDAO.desfiles.put( desfile.getId(), desfile );
		if(desfile.equals(DesfileDAO.desfiles.get(desfile.getId())))
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
		Desfile desfile = new Desfile();
		if(entidade instanceof Desfile)
		{
			desfile = (Desfile) entidade;	
		}
		else
		{
			return false;
		}

		if (DesfileDAO.desfiles.containsKey(desfile.getId())) {
			return DesfileDAO.desfiles.put(desfile.getId(), desfile) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Desfile desfile = new Desfile();
		if(entidade instanceof Desfile)
		{
			desfile = (Desfile) entidade;	
		}
		else
		{
			return false;
		}

		return (DesfileDAO.desfiles.remove(desfile.getId())!= null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> desfiles = new ArrayList<>();
		for(int i=0; i< DesfileDAO.desfiles.size(); i++ )
		{
			desfiles.add(DesfileDAO.desfiles.get(i));
		}
		return desfiles;
	}

	@Override
	public Collection<Entidade> obterTodosCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		return DesfileDAO.desfiles.get(numero);
	}

}
