package dao;

import java.util.HashMap;
import java.util.Map;

import negocio.Integrante;

public class IntegranteDAO {

	public static final Map< String, Integrante> integrantes = new HashMap<>();
	
	public static String gravar(Integrante integrante) {

		integrante.setId( "" + integrantes.size() );
		
		integrantes.put( integrante.getId(), integrante );
		
		return integrante.getId();
	}

	public static Integrante obtemPorId(String id) {
		return integrantes.get( id );
	}
}
