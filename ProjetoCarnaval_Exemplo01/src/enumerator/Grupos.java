package enumerator;

public enum Grupos
{
	GrupoEspecial( "Grupo Especial"), 
	SerieA( "Serie A"), 
	GrupoB( "Grupo B"), 
	GrupoC( "Grupo C"), 
	GrupoD( "Grupo D"), 
	GrupoAvaliacao( "Grupo Avaliação" );
	
	public final String nomeBonito;
	
	Grupos( String nomeBonito ) {
		this.nomeBonito = nomeBonito;
	}
	
	public static Grupos from(String valor) {

		for (Grupos g : Grupos.values()) {
			if (g.nomeBonito.equalsIgnoreCase(valor)) {
				return g;
			}
		}

		return null;
	}
}
