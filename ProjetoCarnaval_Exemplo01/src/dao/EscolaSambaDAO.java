package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumerator.Grupos;
import negocio.Entidade;
import negocio.EscolaSamba;

public class EscolaSambaDAO extends AbstractDAO implements DAO
{
	private static Map< Integer, EscolaSamba > escolas = new HashMap<>();
	
	//Dados de teste. As vari�veis est�o privadas ent�o n�o temos risco de acesso externo.
	private EscolaSamba salgueiro;
	private EscolaSamba viradouro;
	private EscolaSamba mangueira;
	{
		this.salgueiro = new EscolaSamba( "salgueiro", "123" );
		this.viradouro = new EscolaSamba( "viradouro", "123" );
		this.mangueira = new EscolaSamba( "mangueira", "123" );
		
		this.salgueiro.setNome( "Salgueiro" );
		this.salgueiro.setCnpj("123.344.456/123-12");
		this.salgueiro.setEnderecoBarracao("endereco Barracao");
		this.salgueiro.setEnderecoQuadra("Endereco Quadra");
		this.salgueiro.setGrupoAtual(Grupos.GrupoEspecial);
		this.salgueiro.setTelefone("2222-2222");
		this.salgueiro.setEmail("@gmail.com");
		this.salgueiro.setId( 0 );
		
		this.viradouro.setNome( "Viradouro" );
		this.viradouro.setCnpj("123.344.456/123-12");
		this.viradouro.setEnderecoBarracao("endereco Barracao");
		this.viradouro.setEnderecoQuadra("Endereco Quadra");
		this.viradouro.setGrupoAtual(Grupos.GrupoEspecial);
		this.viradouro.setTelefone("2222-2222");
		this.viradouro.setEmail("@gmail.com");
		this.viradouro.setId( 1 );
		
		this.mangueira.setNome( "Mangueira" );
		this.mangueira.setCnpj("123.344.456/123-12");
		this.mangueira.setEnderecoBarracao("endereco Barracao");
		this.mangueira.setEnderecoQuadra("Endereco Quadra");
		this.mangueira.setGrupoAtual(Grupos.GrupoEspecial);
		this.mangueira.setTelefone("2222-2222");
		this.mangueira.setEmail("@gmail.com");
		this.mangueira.setId( 2 );
		
		
		
		EscolaSambaDAO.escolas.put( this.salgueiro.getId(),  this.salgueiro);
		EscolaSambaDAO.escolas.put( this.viradouro.getId(),  this.viradouro);
		EscolaSambaDAO.escolas.put( this.mangueira.getId(),  this.mangueira);
	}
	
	public EscolaSamba obterPorNome(String nome)
	{
		for(int i=0; i< EscolaSambaDAO.escolas.size(); i++)
		{
			if(EscolaSambaDAO.escolas.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				return EscolaSambaDAO.escolas.get(i);
			}
		}
		return null;
	}
	
	public List<Entidade> obterListaPorNome(String nome)
	{
		List<Entidade> escolasSamba = new ArrayList<>();
		
		for(int i=0; i< EscolaSambaDAO.escolas.size(); i++)
		{
			if(EscolaSambaDAO.escolas.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				escolasSamba.add(EscolaSambaDAO.escolas.get(i));
			}
		}
		return escolasSamba;
	}

	@Override
	public boolean cadastrar(Entidade entidade) {
		EscolaSamba escolaSamba = new EscolaSamba("","");
		if(entidade instanceof EscolaSamba)
		{
			escolaSamba = (EscolaSamba) entidade;	
		}
		else
		{
			return false;
		}

		escolaSamba.setId( EscolaSambaDAO.escolas.size() );

		if(EscolaSambaDAO.escolas.put( escolaSamba.getId(), escolaSamba ) != null)
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
		EscolaSamba escolaSamba = new EscolaSamba("","");
		if(entidade instanceof EscolaSamba)
		{
			escolaSamba = (EscolaSamba) entidade;	
		}
		else
		{
			return false;
		}
		
		if (EscolaSambaDAO.escolas.containsKey(escolaSamba.getId())) {
			return EscolaSambaDAO.escolas.put(escolaSamba.getId(), escolaSamba) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean excluir(Entidade entidade) {
		EscolaSamba escolaSamba = new EscolaSamba("","");
		if(entidade instanceof EscolaSamba)
		{
			escolaSamba = (EscolaSamba) entidade;	
		}
		else
		{
			return false;
		}
		
		return (EscolaSambaDAO.escolas.remove(escolaSamba.getId()) != null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> escolasSamba = new ArrayList<>();
		for(int i=0; i< EscolaSambaDAO.escolas.size(); i++ )
		{
			escolasSamba.add(EscolaSambaDAO.escolas.get(i));
		}
		return escolasSamba;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		return escolas.get( numero );
	}

	@Override
	public Collection<Entidade> obterTodosCollection() {
		List<Entidade> escolasSamba = new ArrayList<>();
		for(int i=0; i< EscolaSambaDAO.escolas.size(); i++ )
		{
			escolasSamba.add(EscolaSambaDAO.escolas.get(i));
		}
		return escolasSamba;
	}
}
