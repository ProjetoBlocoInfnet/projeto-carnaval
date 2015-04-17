package negocio;

public class Jurado extends Pessoa
{
	public Jurado(String login, String senha) {
		super(login, senha);
	}

	private Quesito quesitoJulgado;

	public Quesito getQuesitoJulgado() {
		return quesitoJulgado;
	}

	public void setQuesitoJulgado(Quesito quesitoJulgado) {
		this.quesitoJulgado = quesitoJulgado;
	}
}
