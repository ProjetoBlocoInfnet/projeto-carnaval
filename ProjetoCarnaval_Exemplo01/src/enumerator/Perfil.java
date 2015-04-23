package enumerator;

public enum Perfil {

	ADMINISTRADOR( "indexAdmin.jsp" ),
	TORCEDOR( "indexTorcedor.jsp" ),
	INTEGRANTE( "indexIntegrante.jsp" ),
	ESCOLASAMBA( "indexEscolaSamba.jsp" );

	public final String indexPage;

	Perfil( String indexPage ) {
		this.indexPage = indexPage;
	}
}
