package enumerator;

public enum Perfil {

	ADMINISTRADOR( "indexAdmin.jsp", "Administrador" ),
	TORCEDOR( "indexTorcedor.jsp", "Torcedor" ),
	INTEGRANTE( "indexIntegrante.jsp", "Integrante" ),
	ESCOLASAMBA( "indexEscolaSamba.jsp", "Escola de samba" );

	public final String indexPage;
	public final String nomeBonito;

	Perfil( String indexPage, String nomeBonito ) {
		this.indexPage = indexPage;
		this.nomeBonito = nomeBonito;
	}
	
	public static Perfil from(String valor) {

		for (Perfil p : Perfil.values()) {
			if (p.nomeBonito.equalsIgnoreCase(valor)) {
				return p;
			}

			if (p.indexPage.equalsIgnoreCase(valor)) {
				return p;
			}
		}

		return null;
	}
}
