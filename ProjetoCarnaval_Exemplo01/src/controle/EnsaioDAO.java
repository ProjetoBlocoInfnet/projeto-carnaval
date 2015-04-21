package controle;

import java.util.HashMap;
import java.util.Map;

public class EnsaioDAO {
	
	public static final Map< String, Ensaio > ensaios = new HashMap<>();

	public static String grava(Ensaio ensaio) {
		
		ensaio.setId( "" + ensaios.size() );
		
		ensaios.put( ensaio.getId(), ensaio );
		
		return ensaio.getId();
	}

}
