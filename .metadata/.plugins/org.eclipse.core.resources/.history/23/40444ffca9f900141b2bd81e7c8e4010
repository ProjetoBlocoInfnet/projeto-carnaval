package enumerator;

import negocio.Pessoa.Sexos;

public enum Grupos
{
	GrupoEspecial(1, "Grupo Especial"), 
	SerieA(2, "Serie A"), 
	GrupoB(3, "Grupo B"), 
	GrupoC(4, "Grupo C"), 
	GrupoD(5, "Grupo D"), 
	GrupoAvaliacao(6, "Grupo Avalia��o" );
	
	public final Integer id;
	public final String nomeBonito;
	
	Grupos( Integer id, String nomeBonito ) {
		this.id = id;
		this.nomeBonito = nomeBonito;
	}
	

	public static Grupos from(int valor) {

		for (Grupos g : Grupos.values()) {
			if (g.id == valor) {
				return g;
			}
		}

		return null;
	}
}
