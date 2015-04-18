package negocio;

public class Jurado extends Pessoa
{
	public Jurado(String login, String senha) {
		super(login, senha);
		// TODO Auto-generated constructor stub
	}

	private Quesito quesitoJulgado;

	public Quesito getQuesitoJulgado() {
		return quesitoJulgado;
	}

	public void setQuesitoJulgado(Quesito quesitoJulgado) {
		this.quesitoJulgado = quesitoJulgado;
	}
}
