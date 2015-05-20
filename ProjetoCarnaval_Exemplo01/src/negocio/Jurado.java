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

	@Override
	public String toString() {
		return "Jurado [quesitoJulgado=" + quesitoJulgado
				+ ", getQuesitoJulgado()=" + getQuesitoJulgado()
				+ ", getNome()=" + getNome() + ", getEndereco()="
				+ getEndereco() + ", getCpf()=" + getCpf() + ", getCep()="
				+ getCep() + ", getTelefone()=" + getTelefone()
				+ ", getEmail()=" + getEmail() + ", getSexo()=" + getSexo()
				+ ", getId()=" + getId() + ", obterMensagemAcesso()="
				+ obterMensagemAcesso() + ", getLogin()=" + getLogin()
				+ ", getSenha()=" + getSenha() + ", getPerfil()=" + getPerfil()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
