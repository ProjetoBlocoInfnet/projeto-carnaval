package negocio;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import enumerator.Grupos;

public class Desfile implements Entidade
{
	private Integer id;
	private Date data_primeiro_desfile;
	private Date data_ultimo_desfile;
	private Grupos grupo;
	private Map<Integer,Quesito> quesitos = new HashMap<>();
	private Set<EscolaSamba> escolasSamba = new HashSet<>();
	private Map<Integer,Date> data_hora_desfile_escolas = new HashMap<>();
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
	public Date getData_primeiro_desfile() {
		return data_primeiro_desfile;
	}
	public void setData_primeiro_desfile(Date data_primeiro_desfile) {
		this.data_primeiro_desfile = data_primeiro_desfile;
	}
	public Date getData_ultimo_desfile() {
		return data_ultimo_desfile;
	}
	public void setData_ultimo_desfile(Date data_ultimo_desfile) {
		this.data_ultimo_desfile = data_ultimo_desfile;
	}
	public Grupos getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupos grupo) {
		this.grupo = grupo;
	}
	public Map<Integer, Quesito> getQuesitos() {
		return quesitos;
	}
	public void setQuesitos(Map<Integer, Quesito> quesitos) {
		this.quesitos = quesitos;
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
	public Map<Integer, Date> getData_hora_desfile_escolas() {
		return data_hora_desfile_escolas;
	}
	public void setData_hora_desfile_escolas(
			Map<Integer, Date> data_hora_desfile_escolas) {
		this.data_hora_desfile_escolas = data_hora_desfile_escolas;
	}
}
