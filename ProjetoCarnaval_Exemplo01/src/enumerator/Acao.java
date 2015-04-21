package enumerator;

public enum Acao
{
	carpinteiro,ala;
	
	public static Acao from( String nome ) {
		
		if ( nome == null ) {
			return null;
		}
		
		return Acao.valueOf( nome.toLowerCase() );
	}
}
