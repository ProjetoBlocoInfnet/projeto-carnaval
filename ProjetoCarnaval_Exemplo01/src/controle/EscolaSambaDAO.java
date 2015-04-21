package controle;

import java.util.HashMap;
import java.util.Map;

import negocio.EscolaSamba;

public class EscolaSambaDAO {
	
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

}
