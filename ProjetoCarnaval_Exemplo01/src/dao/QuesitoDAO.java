package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import negocio.Entidade;
import negocio.Quesito;

public class QuesitoDAO extends AbstractDAO implements DAO
{
	private PreparedStatement pstmt;
	private ResultSet rs;

//	private static Map<Integer, Quesito> quesitos = new HashMap<>(); //TODO n�o estamos usando banco ainda. Por enquanto isso fica aqui.

	public Quesito obterPorNome(String nome)
	{
		/*for(int i=0; i<QuesitoDAO.quesitos.size(); i++)
		{
			if(QuesitoDAO.quesitos.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				return QuesitoDAO.quesitos.get(i);
			}
		}
		return null;*/
		Quesito q = null;
		Connection c = getConnection();
		String sql = "Select * from quesito where nome_quesito = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, nome);
			rs = pstmt.executeQuery();
			while(rs.next()){
				q = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return q;
	}
	
	public List<Entidade> obterListaPorNome(String nome)
	{
		List<Entidade> listQuesitos = new ArrayList<>();
		
		/*for(int i=0; i< QuesitoDAO.quesitos.size(); i++)
		{
			if(QuesitoDAO.quesitos.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				listQuesitos.add(QuesitoDAO.quesitos.get(i));
			}
		}
		return listQuesitos;*/
		Connection c = getConnection();
		String sql = "Select * from quesito where nome_quesito like ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, "%" + nome + "%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				listQuesitos.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return listQuesitos;
	}

	@Override
	public boolean cadastrar(Entidade entidade) {
		Quesito quesito = new Quesito();
		if(entidade instanceof Quesito)
		{
			quesito = (Quesito) entidade;	
		}
		else
		{
			return false;
		}

		Connection c = getConnection();
		String sql = "insert into quesito(nome_quesito,descricao)values(?,?);";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, quesito.getNome());
			pstmt.setString(2, quesito.getDescricao());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(c);
		}
		return true;

		/*quesito.setId( QuesitoDAO.quesitos.size() );

		if(QuesitoDAO.quesitos.put( quesito.getId(), quesito ) != null)
		{
			return true;
		}
		else
		{
			return false;
		}*/
	}

	@Override
	public boolean alterar(Entidade entidade) {
		Quesito quesito = new Quesito();
		if(entidade instanceof Quesito)
		{
			quesito = (Quesito) entidade;	
		}
		else
		{
			return false;
		}

		Connection c = getConnection();
		String sqlUpdate = "update quesito set nome_quesito = ?, descricao = ?, where id_quesito = ?;";
		try {
			c.setAutoCommit(false);
			pstmt = c.prepareStatement(sqlUpdate);
			pstmt.setString(1, quesito.getNome());
			pstmt.setString(2, quesito.getDescricao());
			pstmt.setInt(3, quesito.getId());
			pstmt.execute();
			pstmt.close();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			closeConnection(c);
		}
		return true;
		/*if (QuesitoDAO.quesitos.containsKey(quesito.getId())) {
			return QuesitoDAO.quesitos.put(quesito.getId(), quesito) != null;
		} else {
			return false;
		}*/
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Quesito quesito = new Quesito();
		if(entidade instanceof Quesito)
		{
			quesito = (Quesito) entidade;	
		}
		else
		{
			return false;
		}

		Connection c = getConnection();
		String sql = "Delete from quesito where id_quesito =?";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, quesito.getId());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(c);
		}
		
		return true;
		//return (QuesitoDAO.quesitos.remove(quesito.getId()) != null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> quesitos = new ArrayList<>();
		Connection c = getConnection();
		String sql = "Select * from quesito;";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				quesitos.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return quesitos;
		/*for(int i=0; i< QuesitoDAO.quesitos.size(); i++ )
		{
			quesito.add(QuesitoDAO.quesitos.get(i));
		}
		return quesito;*/
	}

	private Quesito resultSet2Object(ResultSet rs) throws SQLException
	{
		Quesito q = new Quesito();
		q.setId(rs.getInt("id_quesito"));
		q.setNome(rs.getString("nome_quesito"));
		q.setDescricao(rs.getString("descricao"));
		return q;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		//return QuesitoDAO.quesitos.get(numero);
		Quesito q = null;
		Connection c = getConnection();
		String sql = "Select * from quesito where id_quesito = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				q = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return q;
	}

	@Override
	public Collection<Entidade> obterTodosCollection() {
		/*List<Entidade> quesito = new ArrayList<>();
		for(int i=0; i< QuesitoDAO.quesitos.size(); i++ )
		{
			quesito.add(QuesitoDAO.quesitos.get(i));
		}
		return quesito;*/
		return this.obterTodos();
	}
}
