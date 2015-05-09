package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import negocio.Acao;
import negocio.Entidade;

public class AcaoDAO extends AbstractDAO implements DAO
{
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public boolean cadastrar(Entidade entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Entidade entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> acoes = new ArrayList<>();
		Connection c = getConnection();
		String sql = "Select * from acao;";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				acoes.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return acoes;
	}

	private Acao resultSet2Object(ResultSet rs) throws SQLException
	{
		Acao a = new Acao();
		a.setId(rs.getInt("id_acao"));
		a.setNome(rs.getString("nome"));
		a.setDescricao(rs.getString("descricao"));
		return a;
	}
	
	@Override
	public Collection<Entidade> obterTodosCollection() {
		return this.obterTodos();
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		Acao a = null;
		Connection c = getConnection();
		String sql = "Select * from acao where id_acao = ?;";
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

}
