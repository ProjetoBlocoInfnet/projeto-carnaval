package controle;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import negocio.Ensaio;
import negocio.EscolaSamba;

public class EnsaioDAO {
	
	public static final Map< String, Ensaio > ensaios = new HashMap<>();

	static {
		
		EscolaSamba samba = EscolaSambaDAO.obtemPorId( "0" );
		Ensaio ensaio = new Ensaio( samba, new Date());
		EnsaioDAO.grava( ensaio );
	}
	
	public static String grava(Ensaio ensaio) {
		
		ensaio.setId( "" + ensaios.size() );
		
		ensaios.put( ensaio.getId(), ensaio );
		
		return ensaio.getId();
	}

	public static Collection< Ensaio > obtemTodos() {
		return ensaios.values();
	}
}
