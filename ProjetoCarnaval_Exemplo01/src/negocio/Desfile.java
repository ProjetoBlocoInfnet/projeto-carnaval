package negocio;

import java.util.HashSet;
import java.util.Set;

import enumerator.Grupos;

public class Desfile implements Entidade
{
	private Integer id;
	private Grupos grupo;
	private Set<EscolaSamba> escolasSamba = new HashSet<>();
	private Set<Jurado> jurados = new HashSet<>();

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Grupos getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupos grupo) {
		this.grupo = grupo;
	}
	public Set<EscolaSamba> getEscolasSamba() {
		return escolasSamba;
	}
	public void setEscolasSamba(Set<EscolaSamba> escolasSamba) {
		this.escolasSamba = escolasSamba;
	}
	public Set<Jurado> getJurados() {
		return jurados;
	}
	public void setJurados(Set<Jurado> jurados) {
		this.jurados = jurados;
	}
}
