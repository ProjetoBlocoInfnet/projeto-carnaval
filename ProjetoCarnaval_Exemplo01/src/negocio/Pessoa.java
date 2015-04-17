package negocio;

public abstract class Pessoa extends Usuario {

	public enum Sexos {
		MASCULINO("Masculino", "M"), FEMININO("Feminino", "F");

		public final String nomeBonito;
		public final String sigla;

		Sexos(String nomeBonito, String sigla) {
			this.nomeBonito = nomeBonito;
			this.sigla = sigla;
		}

		public String toString() {
			return nomeBonito;
		}

		public static Sexos from(String valor) {

			for (Sexos s : Sexos.values()) {
				if (s.nomeBonito.equalsIgnoreCase(valor)) {
					return s;
				}

				if (s.sigla.equalsIgnoreCase(valor)) {
					return s;
				}
			}

			return null;
		}
	}

	
	public Pessoa(String login, String senha) {
		super( login, senha );
	}
	
	private String nome;
	private String endereco;
	private String cpf;
	private String cep;
	private String telefone;
	private String email;
	private Sexos sexo;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Sexos getSexo() {
		return sexo;
	}

	public void setSexo(Sexos sexo) {
		this.sexo = sexo;
	}
}
