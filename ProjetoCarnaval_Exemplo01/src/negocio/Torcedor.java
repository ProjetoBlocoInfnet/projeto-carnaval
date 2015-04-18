package negocio;

public class Torcedor extends Pessoa
{
	public Torcedor(String login, String senha) {
		super(login, senha);
		// TODO Auto-generated constructor stub
	}

	private EscolaSamba escolaSambaCoracao;

	public EscolaSamba getEscolaSamba() {
		return escolaSambaCoracao;
	}

	public void setEscolaSamba(EscolaSamba escolaSamba) {
		this.escolaSambaCoracao = escolaSamba;
	}
}
