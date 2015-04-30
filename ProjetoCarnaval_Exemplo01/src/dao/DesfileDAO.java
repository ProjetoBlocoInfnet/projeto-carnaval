package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import enumerator.Grupos;
import negocio.Desfile;
import negocio.Entidade;
import negocio.EscolaSamba;
import negocio.Jurado;

public class DesfileDAO extends AbstractDAO implements DAO {

	private static Map<Integer,Desfile> desfiles = new HashMap<>();
	
	{
		Desfile d = new Desfile();
		d.setId(0);
		d.setData(new java.sql.Date(15000));
		Set<EscolaSamba> escolas = new HashSet<EscolaSamba>();
		escolas.add((EscolaSamba) new EscolaSambaDAO().obterPorId(0));
		escolas.add((EscolaSamba) new EscolaSambaDAO().obterPorId(1));
		d.setEscolasSamba(escolas);
		d.setGrupo(Grupos.GrupoEspecial);
		Set<Jurado> jurados = new HashSet<Jurado>();
		jurados.add((Jurado) new JuradoDAO().obterPorId(0));
		jurados.add((Jurado) new JuradoDAO().obterPorId(1));
		d.setJurados(jurados);
		DesfileDAO.desfiles.put(0, d);
		
		d = new Desfile();
		d.setId(0);
		d.setData(new java.sql.Date(17000));
		escolas = new HashSet<EscolaSamba>();
		escolas.add((EscolaSamba) new EscolaSambaDAO().obterPorId(1));
		escolas.add((EscolaSamba) new EscolaSambaDAO().obterPorId(2));
		d.setEscolasSamba(escolas);
		d.setGrupo(Grupos.GrupoD);
		jurados = new HashSet<Jurado>();
		jurados.add((Jurado) new JuradoDAO().obterPorId(1));
		d.setJurados(jurados);
		DesfileDAO.desfiles.put(1, d);
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
