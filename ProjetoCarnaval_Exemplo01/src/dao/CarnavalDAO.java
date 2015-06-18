package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import enumerator.Grupos;
import negocio.Desfile;
import negocio.Entidade;
import negocio.EscolaSamba;

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
			pstmt.setDate(2,new java.sql.Date(desfile.getData_primeiro_desfile().getTime()));
			pstmt.setDate(3,new java.sql.Date(desfile.getData_ultimo_desfile().getTime()));
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(c);
		}
		return false;
	}

	public boolean cadastrarEscolaCarnaval(Entidade entidade) {
		Desfile desfile = new Desfile();
		if (entidade instanceof Desfile) {
			desfile = (Desfile) entidade;
		} else {
			return false;
		}
		
		for(EscolaSamba es : desfile.getEscolasSamba())
		{
			Connection c = getConnection();
			String sql = "insert into carnaval_desfile_escola(escola_samba_id_escola_samba,carnaval_id_carnaval,data_hora_inicio)values(?,?,?);";
			try {
				pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, es.getId());
				pstmt.setInt(2, desfile.getId());
				pstmt.setDate(3,new java.sql.Date(desfile.getData_hora_desfile_escolas().get(es.getId()).getTime()));
				pstmt.execute();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeConnection(c);
			}
		}
		return false;
	}

	@Override
	public boolean alterar(Entidade entidade) {
		Desfile desfile = new Desfile();
		if (entidade instanceof Desfile) {
			desfile = (Desfile) entidade;
		} else {
			return false;
		}
		
		Connection c = getConnection();
		String sql = "update carnaval set grupos_id_grupo = ?, data_primeiro_desfile = ?, data_ultimo_desfile = ? where id_carnaval = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, desfile.getGrupo().id);
			pstmt.setDate(2,new java.sql.Date(desfile.getData_primeiro_desfile().getTime()));
			pstmt.setDate(3,new java.sql.Date(desfile.getData_ultimo_desfile().getTime()));
			pstmt.setInt(4, desfile.getId());
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
	public boolean excluir(Entidade entidade) {
		Desfile desfile = new Desfile();
		if (entidade instanceof Desfile) {
			desfile = (Desfile) entidade;
		} else {
			return false;
		}
		
		Connection c = getConnection();
		String sql = "delete from carnaval where id_carnaval = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, desfile.getId());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(c);
		}
		return false;	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> desfiles = new ArrayList<>();
		Connection c = getConnection();
		String sql = "Select * from carnaval;";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				desfiles.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return desfiles;
	}

	private Desfile resultSet2Object(ResultSet rs) throws SQLException
	{
		Desfile d = new Desfile();
		d.setId(rs.getInt("id_carnaval"));
		d.setData_primeiro_desfile(rs.getDate("data_primeiro_desfile"));
		d.setData_ultimo_desfile(rs.getDate("data_ultimo_desfile"));
		d.setGrupo(Grupos.from(rs.getInt("grupos_id_grupo")));
		return d;
	}
	
	@Override
	public Collection<Entidade> obterTodosCollection() {
		return this.obterTodos();
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		Desfile d = null;
		Connection c = getConnection();
		String sql = "Select * from carnaval where id_carnaval = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				d = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return d;
	}

}
