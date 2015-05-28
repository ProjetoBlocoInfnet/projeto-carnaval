package enumerator;

public enum Perfil {

	ADMINISTRADOR(1,  "indexAdmin.jsp", "Administrador" ),
	TORCEDOR( 4, "areaTorcedor/index.jsp", "Torcedor" ),
	INTEGRANTE( 3, "indexIntegrante.jsp", "Integrante" ),
	ESCOLASAMBA( 2, "indexEscolaSamba.jsp", "Escola de samba" );

	public final Integer id;
	public final String indexPage;
	public final String nomeBonito;

	Perfil( Integer id, String indexPage, String nomeBonito ) {
		this.id = id;
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
	
	public static Perfil from(Integer valor) {

		for (Perfil p : Perfil.values()) {
			if (p.id == valor) {
				return p;
			}
		}

		return null;
	}
}
