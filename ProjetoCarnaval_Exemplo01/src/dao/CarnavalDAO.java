package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import negocio.Desfile;
import negocio.Entidade;

public class CarnavalDAO extends AbstractDAO implements DAO {
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public boolean cadastrar(Entidade entidade) {
		Desfile desfile = new Desfile();
		if (entidade instanceof Desfile) {
			desfile = (Desfile) entidade;
		} else {
			return false;
		}
		
		Connection c = getConnection();
		String sql = "insert into carnaval(grupos_id_grupo, data_primeiro_desfile, data_ultimo_desfile)values(?,?,?);";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, desfile.getGrupo().id);
			pstmt.setDate(4,new java.sql.Date(desfile.getData_primeiro_desfile().getTime()));
			pstmt.setDate(5,new java.sql.Date(desfile.getData_ultimo_desfile().getTime()));
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(c);
		}
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Entidade> obterTodosCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

}
