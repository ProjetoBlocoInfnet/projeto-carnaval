package negocio;

public class Jurado extends Pessoa
{
	private Quesito quesitoJulgado;

	public Quesito getQuesitoJulgado() {
		return quesitoJulgado;
	}

	public void setQuesitoJulgado(Quesito quesitoJulgado) {
		this.quesitoJulgado = quesitoJulgado;
	}
}
