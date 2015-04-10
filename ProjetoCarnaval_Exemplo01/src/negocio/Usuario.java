package negocio;

public class Usuario {

	private String login;
	private String senha;
	private Galera galera;
	
	public String obterMensagemAcesso(){
		return String.format("O login %s com senha %s est� logado pela galera %s", 
							this.getLogin(),
							this.getSenha(),
							this.getGalera().getNome());
	}
	
	public Usuario(String login, String senha) {
		this.setLogin(login);
		this.setSenha(senha);
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Galera getGalera() {
		return galera;
	}
	public void setGalera(Galera galera) {
		this.galera = galera;
	}
}
