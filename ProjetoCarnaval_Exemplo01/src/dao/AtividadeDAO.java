package dao;

import java.util.HashMap;
import java.util.Map;

import negocio.Atividade;

public class AtividadeDAO extends AbstractDAO
{
	public static final Map< String, Atividade> atividades = new HashMap<>();
	
	public static String gravar(Atividade atividade)
	{
		atividade.setId( "" + atividades.size() );
		
		atividades.put( atividade.getId(), atividade );
		
		return atividade.getId();
	}

	public static Atividade obtemPorId(String id)
	{
		return atividades.get( id );
	}

}
