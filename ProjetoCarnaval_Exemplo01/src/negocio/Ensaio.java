package negocio;

import java.util.Date;

public class Ensaio implements Entidade
{
	private Integer id;
	private EscolaSamba escola;
	private Date data;

	public Ensaio()
	{
		
	}
	
	public Ensaio(EscolaSamba escola, Date data) {
		this.escola = escola;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public EscolaSamba getEscola() {
		return escola;
	}

	public void setEscola(EscolaSamba escola) {
		this.escola = escola;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
