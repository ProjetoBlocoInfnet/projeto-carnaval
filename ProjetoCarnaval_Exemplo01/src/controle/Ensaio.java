package controle;

import java.util.Date;

import negocio.EscolaSamba;

public class Ensaio {

	public final EscolaSamba escola;
	public final Date data;
	
	private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId( String id ) {
		this.id = id;
	}
	
	public Ensaio(EscolaSamba escola, Date data) {
		this.escola = escola;
		this.data = data;
	}
}
