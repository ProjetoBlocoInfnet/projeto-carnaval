package dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import negocio.EscolaSamba;

public class EscolaSambaDAO extends AbstractDAO
{
	private Set<EscolaSamba> escolasSamba = new HashSet<EscolaSamba>(); //TODO não estamos usando banco ainda. Por enquanto isso fica aqui.

	public static final Map< String, EscolaSamba > escolas = new HashMap<>();
	
	//só pra debug
	static {
		EscolaSamba salgueiro = new EscolaSamba( "salgueiro", "123" );
		EscolaSamba viradouro = new EscolaSamba( "viradouro", "123" );
		EscolaSamba mangueira = new EscolaSamba( "mangueira", "123" );
		
		salgueiro.setNome( "Salgueiro" );
		viradouro.setNome( "Viradouro" );
		mangueira.setNome( "Mangueira" );
		salgueiro.setId( "0" );
		viradouro.setId( "1" );
		mangueira.setId( "2" );
		
		escolas.put( salgueiro.getId(),  salgueiro);
		escolas.put( viradouro.getId(),  viradouro);
		escolas.put( mangueira.getId(),  mangueira);
		
		
	}
	
	public static EscolaSamba obtemPorId(String id) {
		return escolas.get( id );
	}

	public static EscolaSamba obtemPeloNome(String parameter) {

		for( EscolaSamba es : escolas.values() ) {
			if ( es.getNome().equalsIgnoreCase( parameter ) ) {
				return es;
			}
		}
		
		return null;
	}

	public boolean inserir(EscolaSamba escolaSamba)
	{
		return this.escolasSamba.add(escolaSamba); // ADD retorna boolean então satisfaz a checagem
	}

	public boolean alterar(EscolaSamba quesito)
	{
		return true;
	}

	public EscolaSamba obterPorNome(String nome)
	{
		for(EscolaSamba e : escolasSamba)
		{
			if(e.getNome().matches("(.*)" + nome + "(.*)"))
			{
				return e;
			}
		}
		return null;
	}
}
