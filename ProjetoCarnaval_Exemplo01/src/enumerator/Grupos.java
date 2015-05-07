package enumerator;

public enum Grupos
{
	GrupoEspecial(1, "Grupo Especial"), 
	SerieA(2, "Serie A"), 
	GrupoB(3, "Grupo B"), 
	GrupoC(4, "Grupo C"), 
	GrupoD(5, "Grupo D"), 
	GrupoAvaliacao(6, "Grupo Avaliação" );
	
	public final Integer id;
	public final String nomeBonito;
	
	Grupos( Integer id, String nomeBonito ) {
		this.id = id;
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
	
	public static Grupos from(Integer valor) {

		for (Grupos g : Grupos.values()) {
			if (g.id == valor) {
				return g;
			}
		}

		return null;
	}
}
