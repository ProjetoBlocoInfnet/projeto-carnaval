package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import negocio.Acao;
import negocio.Atividade;
import negocio.Entidade;
import negocio.EscolaSamba;

public class AtividadeDAO extends AbstractDAO implements DAO
{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
//	private static Map<Integer, Atividade> atividades = new HashMap<>();

	@Override
	public boolean cadastrar(Entidade entidade) {
		Atividade atividade = new Atividade();
		if (entidade instanceof Atividade) {
			atividade = (Atividade) entidade;
		} else {
			return false;
		}

		/*atividade.setId(AtividadeDAO.atividades.size());

		if (AtividadeDAO.atividades.put(atividade.getId(), atividade) != null) {
			return true;
		} else {
			return false;
		}*/
		Connection c = getConnection();
		String sql = "insert into atividade_integrante_escola(integrante_id_integrante,acao_id_acao,escola_samba_id_escola_samba,data_inicio,data_fim)values(?,?,?,?,?);";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, atividade.getId_integrante());
			pstmt.setInt(2, atividade.getAcao().getId());
			pstmt.setInt(3, atividade.getEscolaSamba().getId());
			pstmt.setDate(4,new java.sql.Date(atividade.getData_inicio().getTime()));
			pstmt.setDate(5,new java.sql.Date(atividade.getData_fim().getTime()));
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(c);
		}

		return true;
	}

	@Override
	public boolean alterar(Entidade entidade) {
		Atividade atividade = new Atividade();
		if (entidade instanceof Atividade) {
			atividade = (Atividade) entidade;
		} else {
			return false;
		}

		/*if (AtividadeDAO.atividades.containsKey(atividade.getId())) {
			return AtividadeDAO.atividades.put(atividade.getId(), atividade) != null;
		} else {
			return false;
		}*/
		
		Connection c = getConnection();
		String sql = "update atividade_integrante_escola set integrante_id_integrante = ?,acao_id_acao = ?,escola_samba_id_escola_samba = ?,data_inicio = ?,data_fim = ? where id_atividade = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, atividade.getId_integrante());
			pstmt.setInt(2, atividade.getAcao().getId());
			pstmt.setInt(3, atividade.getEscolaSamba().getId());
			pstmt.setDate(4,new java.sql.Date(atividade.getData_inicio().getTime()));
			pstmt.setDate(5,new java.sql.Date(atividade.getData_fim().getTime()));
			pstmt.setInt(6, atividade.getId());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(c);
		}

		return true;
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Atividade atividade = new Atividade();
		if (entidade instanceof Atividade) {
			atividade = (Atividade) entidade;
		} else {
			return false;
		}

		//return (AtividadeDAO.atividades.remove(atividade.getId()) != null);
		Connection c = getConnection();
		String sql = "Delete from atividade_integrante_escola where id_atividade =?";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, atividade.getId());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(c);
		}
		
		return true;
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> atividades = new ArrayList<>();
		/*for (int i = 0; i < AtividadeDAO.atividades.size(); i++) {
			atividades.add(AtividadeDAO.atividades.get(i));
		}*/
		Connection c = getConnection();
		String sql = "Select * from atividade_integrante_escola;";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				atividades.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return atividades;
	}

	private Atividade resultSet2Object(ResultSet rs) throws SQLException
	{
		Atividade a = new Atividade();
		a.setId(rs.getInt("id_atividade"));
		a.setId_integrante(rs.getInt("integrante_id_integrante"));
		a.setEscolaSamba((EscolaSamba) new EscolaSambaDAO().obterPorId(rs.getInt("escola_samba_id_escola_samba")));
		a.setAcao((Acao) new AcaoDAO().obterPorId(rs.getInt("acao_id_acao")));
		a.setData_inicio(rs.getDate("data_inicio"));
		a.setData_fim(rs.getDate("data_fim"));
		return a;
	}
	
	public List<Entidade> obterTodosPorIdIntegrante(Integer idIntegrante) {
		List<Entidade> atividades = new ArrayList<>();
		/*for (int i = 0; i < AtividadeDAO.atividades.size(); i++) {
			atividades.add(AtividadeDAO.atividades.get(i));
		}*/
		Connection c = getConnection();
		String sql = "Select * from atividade_integrante_escola where integrante_id_integrante = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, idIntegrante);
			rs = pstmt.executeQuery();
			while(rs.next()){
				atividades.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return atividades;
	}

	public List<Entidade> obterTodosPorIdIntegranteNestaEscola(Integer idIntegrante, Integer idEscolaSamba) {
		List<Entidade> atividades = new ArrayList<>();
		/*for (int i = 0; i < AtividadeDAO.atividades.size(); i++) {
			atividades.add(AtividadeDAO.atividades.get(i));
		}*/
		Connection c = getConnection();
		String sql = "Select * from atividade_integrante_escola where integrante_id_integrante = ? and escola_samba_id_escola_samba = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, idIntegrante);
			pstmt.setInt(2, idEscolaSamba);
			rs = pstmt.executeQuery();
			while(rs.next()){
				atividades.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return atividades;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		//return AtividadeDAO.atividades.get(numero);
		Atividade a = null;
		Connection c = getConnection();
		String sql = "Select * from atividade_integrante_escola where id_atividade = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				a = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return a;
	}

	@Override
	public Collection<Entidade> obterTodosCollection() {
		/*List<Entidade> atividades = new ArrayList<>();
		for (int i = 0; i < AtividadeDAO.atividades.size(); i++) {
			atividades.add(AtividadeDAO.atividades.get(i));
		}
		return atividades;*/
		return this.obterTodos();
	}

}
