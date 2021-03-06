package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import negocio.Ensaio;
import negocio.Entidade;
import negocio.EscolaSamba;

public class EnsaioDAO extends AbstractDAO implements DAO
{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/*private static Map< Integer, Ensaio > ensaios = new HashMap<>();


	/*private EscolaSamba samba;
	private Ensaio ensaio;
	{
	this.samba = (EscolaSamba) new EscolaSambaDAO().obterPorId( 0 );
	this.ensaio = new Ensaio( this.samba, new Date());
	this.cadastrar( this.ensaio );
	}*/

	@Override
	public boolean cadastrar(Entidade entidade)
	{
		Ensaio ensaio = new Ensaio();
		if(entidade instanceof Ensaio)
		{
			ensaio = (Ensaio) entidade;	
		}
		else
		{
			return false;
		}

		Connection c = getConnection();
		String sql = "insert into ensaio(escola_samba_id_escola_samba,data_ensaio)values(?,?);";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, ensaio.getEscola().getId());
			pstmt.setDate(2,new java.sql.Date(ensaio.getData().getTime()));
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(c);
		}
		return true;
		/*ensaio.setId( EnsaioDAO.ensaios.size() );

		if(EnsaioDAO.ensaios.put( ensaio.getId(), ensaio ) != null)
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
		Ensaio ensaio = new Ensaio();
		if(entidade instanceof Ensaio)
		{
			ensaio = (Ensaio) entidade;	
		}
		else
		{
			return false;
		}

		Connection c = getConnection();
		String sqlUpdate = "update ensaio set data_ensaio = ?, where id_ensaio = ? and escola_samba_id_escola_samba = ?;";
		try {
			c.setAutoCommit(false);
			pstmt = c.prepareStatement(sqlUpdate);
			pstmt.setDate(1,new java.sql.Date(ensaio.getData().getTime()));
			pstmt.setInt(2, ensaio.getId());
			pstmt.setInt(3, ensaio.getEscola().getId());
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
		/*if (EnsaioDAO.ensaios.containsKey(ensaio.getId())) {
			return EnsaioDAO.ensaios.put(ensaio.getId(), ensaio) != null;
		} else {
			return false;
		}*/
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Ensaio ensaio = new Ensaio();
		if(entidade instanceof Ensaio)
		{
			ensaio = (Ensaio) entidade;	
		}
		else
		{
			return false;
		}

		Connection c = getConnection();
		String sql = "Delete from ensaio where id_ensaio =?";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, ensaio.getId());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(c);
		}
		
		return true;
//		return (EnsaioDAO.ensaios.remove(ensaio.getId()) != null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> ensaios = new ArrayList<>();
		/*for(int i=0; i< EnsaioDAO.ensaios.size(); i++ )
		{
			ensaios.add(EnsaioDAO.ensaios.get(i));
		}
		return ensaios;*/
		Connection c = getConnection();
		String sql = "Select * from ensaio";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ensaios.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return ensaios;
	}
	

	public List<Ensaio> obterTodos(EscolaSamba escola) {
		
		List<Ensaio> ensaios = new ArrayList<>();
		Connection c = getConnection();
		String sql = "select * from ensaio join (escola_samba) on (ensaio.id_ensaio = escola_samba.id_escola_samba) where escola_samba_id_escola_samba = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, escola.getId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				ensaios.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return ensaios;
		
		
	}

	/*
	 * Retorna um objeto passando o ResultSet
	 */
	private Ensaio resultSet2Object(ResultSet rs) throws SQLException
	{
		Ensaio e = new Ensaio();
		e.setId(rs.getInt("id_ensaio"));
		e.setEscola((EscolaSamba) new EscolaSambaDAO().obterPorIdEscola(rs.getInt("escola_samba_id_escola_samba")));
		e.setData(rs.getDate("data_ensaio"));
		return e;
	}
	
	@Override
	public Entidade obterPorId(Integer numero) {
		Ensaio en = null;
		Connection c = getConnection();
		String sql = "Select * from ensaio where id_ensaio = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				en = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return en;
		//return EnsaioDAO.ensaios.get(numero);
	}

	@Override
	public Collection<Entidade> obterTodosCollection()
	{
		return this.obterTodos();
	}

	public List<Entidade> obterPorData(Date data, EscolaSamba escola) {
		
		List<Entidade> ensaios = new ArrayList<>();
		Connection c = getConnection();
		String sql = "select * from ensaio join (escola_samba) on (ensaio.id_ensaio = escola_samba.id_escola_samba) where escola_samba_id_escola_samba = ? and data_ensaio = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, escola.getId());
			pstmt.setDate(2, (java.sql.Date) data);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ensaios.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return ensaios;
		
				
	}
}
