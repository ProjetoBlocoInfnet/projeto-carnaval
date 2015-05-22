package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import negocio.Entidade;
import negocio.EscolaSamba;
import enumerator.Grupos;
import enumerator.Perfil;

public class EscolaSambaDAO extends AbstractDAO implements DAO
{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/*private static Map< Integer, EscolaSamba > escolas = new HashMap<>();
	
	//Dados de teste. As vari�veis est�o privadas ent�o n�o temos risco de acesso externo.
	private EscolaSamba salgueiro;
	private EscolaSamba viradouro;
	private EscolaSamba mangueira;
	{
		this.salgueiro = new EscolaSamba( "salgueiro", "123" );
		this.viradouro = new EscolaSamba( "viradouro", "123" );
		this.mangueira = new EscolaSamba( "mangueira", "123" );
		
		this.salgueiro.setNome( "Salgueiro" );
		this.salgueiro.setCnpj("123.344.456/123-12");
		this.salgueiro.setEnderecoBarracao("endereco Barracao");
		this.salgueiro.setEnderecoQuadra("Endereco Quadra");
		this.salgueiro.setGrupoAtual(Grupos.GrupoEspecial);
		this.salgueiro.setTelefone("2222-2222");
		this.salgueiro.setEmail("@gmail.com");
		this.salgueiro.setId( 0 );
		
		this.viradouro.setNome( "Viradouro" );
		this.viradouro.setCnpj("123.344.456/123-12");
		this.viradouro.setEnderecoBarracao("endereco Barracao");
		this.viradouro.setEnderecoQuadra("Endereco Quadra");
		this.viradouro.setGrupoAtual(Grupos.GrupoEspecial);
		this.viradouro.setTelefone("2222-2222");
		this.viradouro.setEmail("@gmail.com");
		this.viradouro.setId( 1 );
		
		this.mangueira.setNome( "Mangueira" );
		this.mangueira.setCnpj("123.344.456/123-12");
		this.mangueira.setEnderecoBarracao("endereco Barracao");
		this.mangueira.setEnderecoQuadra("Endereco Quadra");
		this.mangueira.setGrupoAtual(Grupos.GrupoEspecial);
		this.mangueira.setTelefone("2222-2222");
		this.mangueira.setEmail("@gmail.com");
		this.mangueira.setId( 2 );
		
		
		
		EscolaSambaDAO.escolas.put( this.salgueiro.getId(),  this.salgueiro);
		EscolaSambaDAO.escolas.put( this.viradouro.getId(),  this.viradouro);
		EscolaSambaDAO.escolas.put( this.mangueira.getId(),  this.mangueira);
	}
	*/
	public EscolaSamba obterPorNome(String nome)
	{
		/*for(int i=0; i< EscolaSambaDAO.escolas.size(); i++)
		{
			if(EscolaSambaDAO.escolas.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				return EscolaSambaDAO.escolas.get(i);
			}
		}
		return null;*/
		EscolaSamba es = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (escola_samba) on (usuario.id_usuario = escola_samba.id_escola_samba) where nome like ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, "%" + nome + "%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				es = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return es;
	}
	
	public List<Entidade> obterListaPorNome(String nome)
	{
		List<Entidade> escolasSamba = new ArrayList<>();
		
		/*for(int i=0; i< EscolaSambaDAO.escolas.size(); i++)
		{
			if(EscolaSambaDAO.escolas.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				escolasSamba.add(EscolaSambaDAO.escolas.get(i));
			}
		}*/
		
		Connection c = getConnection();
		String sql = "select * from usuario join (escola_samba) on (usuario.id_usuario = escola_samba.id_escola_samba) where nome like ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, "%" + nome + "%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				escolasSamba.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}

		return escolasSamba;
	}

	@Override
	public boolean cadastrar(Entidade entidade) {
		EscolaSamba escolaSamba = new EscolaSamba("","");
		if(entidade instanceof EscolaSamba)
		{
			escolaSamba = (EscolaSamba) entidade;	
		}
		else
		{
			return false;
		}

		Connection c = getConnection();
		String sqlUsuario = "insert into usuario (usuario,senha)values(?,?);";
		String sqlIdUsuario = "select * from usuario where usuario = ? and senha = ?;";
		String sqlUsuarioPerfil = "insert into re_usuario_perfil (usuario_id_usuario,perfil_id_perfil)values(?,?);";
		String sqlEscolaSamba = "insert into escola_samba (usuario_id_usuario,nome,endereco_quadra, endereco_barracao, data_fundacao, lema, filiacao, grupos_id_grupo, email, telefone, cnpj)values(?,?,?,?,?,?,?,?,?,?,?);";
		try {
			c.setAutoCommit(false); //caso tudo d� errado, isso permite que eu d� rollback nos inserts
			pstmt = c.prepareStatement(sqlUsuario);
			pstmt.setString(1, escolaSamba.getLogin());
			pstmt.setString(2, escolaSamba.getSenha());
			pstmt.execute();
			//Como o commit dessa opera��o s� pode ser feito no final, precisarei fazer a consulta para pegar o id_usuario aqui mesmo
			//Porque estou na mesma transa��o da inser��o na tabela usuario. Eu n�o conseguiria pegar via UsuarioDAO.
			Integer id_usuario = 0;
			pstmt = c.prepareStatement(sqlIdUsuario);
			pstmt.setString(1, escolaSamba.getLogin());
			pstmt.setString(2, escolaSamba.getSenha());
			rs = pstmt.executeQuery();
			while(rs.next()){
				id_usuario = rs.getInt("id_usuario");
			}
			pstmt = c.prepareStatement(sqlUsuarioPerfil);
			pstmt.setInt(1, id_usuario);
			pstmt.setInt(2, Perfil.ESCOLASAMBA.id);
			pstmt.execute();
			pstmt = c.prepareStatement(sqlEscolaSamba);
			pstmt.setInt(1, id_usuario);
			pstmt.setString(2, escolaSamba.getNome());
			pstmt.setString(3, escolaSamba.getEnderecoQuadra());
			pstmt.setString(4, escolaSamba.getEnderecoBarracao());
			pstmt.setDate(5,new java.sql.Date(escolaSamba.getDataFundacao().getTime()));
			pstmt.setString(6, escolaSamba.getLema());
			pstmt.setString(7, escolaSamba.getFiliacao());
			pstmt.setInt(8, escolaSamba.getGrupoAtual().id);
			pstmt.setString(9, escolaSamba.getEmail());
			pstmt.setString(10, escolaSamba.getTelefone());
			pstmt.setString(11, escolaSamba.getCnpj());
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
		/*escolaSamba.setId( EscolaSambaDAO.escolas.size() );

		if(EscolaSambaDAO.escolas.put( escolaSamba.getId(), escolaSamba ) != null)
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
		EscolaSamba escolaSamba = new EscolaSamba("","");
		if(entidade instanceof EscolaSamba)
		{
			escolaSamba = (EscolaSamba) entidade;	
		}
		else
		{
			return false;
		}
		
		Connection c = getConnection();
		String sqlUpdate = "update escola_samba set nome = ?, endereco_quadra = ?, endereco_barracao = ?, data_fundacao = ?, lema = ?, filiacao = ?, grupos_id_grupo = ?, email = ?, telefone = ?, cnpj = ? where id_escola_samba = ?;";
		try {
			c.setAutoCommit(false);
			pstmt = c.prepareStatement(sqlUpdate);
			pstmt.setString(1, escolaSamba.getNome());
			pstmt.setString(2, escolaSamba.getEnderecoQuadra());
			pstmt.setString(3, escolaSamba.getEnderecoBarracao());
			pstmt.setDate(4,new java.sql.Date(escolaSamba.getDataFundacao().getTime()));
			pstmt.setString(5, escolaSamba.getLema());
			pstmt.setString(6, escolaSamba.getFiliacao());
			pstmt.setInt(7, escolaSamba.getGrupoAtual().id);
			pstmt.setString(8, escolaSamba.getEmail());
			pstmt.setString(9, escolaSamba.getTelefone());
			pstmt.setString(10, escolaSamba.getCnpj());
			pstmt.setInt(11, escolaSamba.getId());
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
		/*if (EscolaSambaDAO.escolas.containsKey(escolaSamba.getId())) {
			return EscolaSambaDAO.escolas.put(escolaSamba.getId(), escolaSamba) != null;
		} else {
			return false;
		}*/
	}

	@Override
	public boolean excluir(Entidade entidade) {
		EscolaSamba escolaSamba = new EscolaSamba("","");
		if(entidade instanceof EscolaSamba)
		{
			escolaSamba = (EscolaSamba) entidade;	
		}
		else
		{
			return false;
		}
		
		/*
		 * A escola de samba ainda deve existir no banco por motivos de hist�rico
		 * A exclus�o do usu�rio � pertencente ao escopo do UsuarioDAO, ent�o, este m�todo invoca UsuarioDAO.excluirUsuario
		 * 
		 */
		return new UsuarioDAO().excluirUsuario(escolaSamba.getLogin(), escolaSamba.getSenha());
		//return (EscolaSambaDAO.escolas.remove(escolaSamba.getId()) != null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> escolasSamba = new ArrayList<>();
		/*for(int i=0; i< EscolaSambaDAO.escolas.size(); i++ )
		{
			escolasSamba.add(EscolaSambaDAO.escolas.get(i));
		}*/
		Connection c = getConnection();
		String sql = "select * from usuario join (escola_samba) on (usuario.id_usuario = escola_samba.usuario_id_usuario);";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				escolasSamba.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return escolasSamba;
	}

	public List<Entidade> obterTodosAtivosPorGrupo(Grupos grupo) {
		List<Entidade> escolasSamba = new ArrayList<>();
		/*for(int i=0; i< EscolaSambaDAO.escolas.size(); i++ )
		{
			escolasSamba.add(EscolaSambaDAO.escolas.get(i));
		}*/
		Connection c = getConnection();
		String sql = "select * from usuario join (escola_samba) on (usuario.id_usuario = escola_samba.usuario_id_usuario) where usuario.ativo = true and grupos_id_grupo = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, grupo.id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				escolasSamba.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return escolasSamba;
	}
	
	public List<Entidade> obterTodosAtivos() {
		List<Entidade> escolasSamba = new ArrayList<>();
		/*for(int i=0; i< EscolaSambaDAO.escolas.size(); i++ )
		{
			escolasSamba.add(EscolaSambaDAO.escolas.get(i));
		}*/
		Connection c = getConnection();
		String sql = "select * from usuario join (escola_samba) on (usuario.id_usuario = escola_samba.usuario_id_usuario) where usuario.ativo = true;";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				escolasSamba.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return escolasSamba;
	}
	
	private EscolaSamba resultSet2Object(ResultSet rs) throws SQLException
	{
		EscolaSamba e = new EscolaSamba(rs.getString("usuario"),rs.getString("senha"));
		e.setId(rs.getInt("id_escola_samba"));
		e.setNome(rs.getString("nome"));
		e.setEnderecoQuadra(rs.getString("endereco_quadra"));
		e.setEnderecoBarracao(rs.getString("endereco_barracao"));
		e.setDataFundacao(rs.getDate("data_fundacao"));
		e.setLema(rs.getString("lema"));
		e.setFiliacao(rs.getString("filiacao"));
		e.setGrupoAtual(Grupos.from(rs.getInt("grupos_id_grupo")));
		e.setEmail(rs.getString("email"));
		e.setTelefone(rs.getString("telefone"));
		e.setCnpj(rs.getString("cnpj"));
		return e;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		//return escolas.get( numero );
		EscolaSamba es = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (escola_samba) on (usuario.id_usuario = escola_samba.usuario_id_usuario) where usuario_id_usuario = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				es = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return es;
	}

	public Entidade obterPorIdEscola(Integer numero) {
		// TODO Auto-generated method stub
		//return escolas.get( numero );
		EscolaSamba es = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (escola_samba) on (usuario.id_usuario = escola_samba.usuario_id_usuario) where id_escola_samba = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				es = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return es;
	}

	
	public Entidade obterAtivoPorId(Integer numero) {
		// TODO Auto-generated method stub
		//return escolas.get( numero );
		EscolaSamba es = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (escola_samba) on (usuario.id_usuario = escola_samba.usuario_id_usuario) where usuario.ativo = true and usuario_id_usuario = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				es = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return es;
	}
	
	@Override
	public Collection<Entidade> obterTodosCollection() {
		/*List<Entidade> escolasSamba = new ArrayList<>();
		for(int i=0; i< EscolaSambaDAO.escolas.size(); i++ )
		{
			escolasSamba.add(EscolaSambaDAO.escolas.get(i));
		}
		return escolasSamba;*/
		return this.obterTodos();
	}

	
}
