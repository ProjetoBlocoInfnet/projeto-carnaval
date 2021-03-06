package negocio;

import enumerator.Perfil;

public class Usuario implements Entidade{

	private String login;
	private String senha;
	private Integer id;
	private Perfil perfil;
	
	public void setId( Integer i ) {
		this.id = i;
	}
	
	public Integer getId() {
		return id;
	}
	
	
	public String obterMensagemAcesso(){
		return String.format("O login %s com senha %s está logado", 
							this.getLogin(),
							this.getSenha());
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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}


}
