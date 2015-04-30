package negocio;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import enumerator.Grupos;

public class Desfile implements Entidade
{
	private Integer id;
	private Date data;
	private Grupos grupo;
	private Set<EscolaSamba> escolasSamba = new HashSet<>();
	private Set<Jurado> jurados = new HashSet<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Desfile other = (Desfile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
