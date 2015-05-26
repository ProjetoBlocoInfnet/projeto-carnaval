package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import enumerator.Perfil;
import negocio.Entidade;
import negocio.Jurado;
import negocio.Quesito;
import negocio.Pessoa.Sexos;

public class JuradoDAO extends AbstractDAO implements DAO
{
	private PreparedStatement pstmt;
	private ResultSet rs;

/*	private static Map<Integer, Jurado> jurados = new HashMap<>();

	{
		Jurado j = new Jurado("","");
		j.setNome("Ricardo");
		JuradoDAO.jurados.put(0, j);
		j = new Jurado("","");
		j.setNome("L�cio");
		JuradoDAO.jurados.put(1, j);
	}
*/	
	public Jurado obterPorNome(String nome)
	{
		/*for(int i=0; i<JuradoDAO.jurados.size(); i++)
		{
			if(JuradoDAO.jurados.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				return JuradoDAO.jurados.get(i);
			}
		}
		return null;*/
		Jurado j = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, jurado) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = jurado.pessoa_id_pessoa) where usuario.ativo = true and nome like ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, "%" + nome + "%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				j = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return j;
	}
	
	public List<Entidade> obterListaPorNome(String nome)
	{
		List<Entidade> listJurados = new ArrayList<>();
		
		/*for(int i=0; i< JuradoDAO.jurados.size(); i++)
		{
			if(JuradoDAO.jurados.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				listJurados.add(JuradoDAO.jurados.get(i));
			}
		}*/
		
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, jurado) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = jurado.pessoa_id_pessoa) where usuario.ativo = true and nome like ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, "%" + nome + "%");
			rs = pstmt.executeQuery();
			while(rs.next()){
				listJurados.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return listJurados;
	}

	@Override
	public boolean alterar(Entidade entidade) {
		Jurado jurado = new Jurado("","");
		if(entidade instanceof Jurado)
		{
			jurado = (Jurado) entidade;	
		}
		else
		{
			return false;
		}

		
		Connection c = getConnection();
		String sqlUpdate = "update pessoa set nome = ?, endereco = ?, cpf = ?, cep = ?, telefone = ?, email = ?, sexo = ? where id_pessoa in (select id_pessoa from pessoa join (integrante) on (pessoa.id_pessoa = integrante.pessoa_id_pessoa) where id_integrante = ?);";
		try {
			c.setAutoCommit(false);
			pstmt = c.prepareStatement(sqlUpdate);
			pstmt.setString(1, jurado.getNome());
			pstmt.setString(2, jurado.getEndereco());
			pstmt.setString(3, jurado.getCpf());
			pstmt.setString(4, jurado.getCep());
			pstmt.setString(5, jurado.getTelefone());
			pstmt.setString(6, jurado.getEmail());
			pstmt.setString(7, jurado.getSexo().sigla);
			pstmt.setInt(8, jurado.getId());
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
		/*jurado.setId( JuradoDAO.jurados.size() );

		if(JuradoDAO.jurados.put( jurado.getId(), jurado ) != null)
		{
			return true;
		}
		else
		{
			return false;
		}*/
	}

	@Override
	public boolean cadastrar(Entidade entidade) {
		Jurado jurado = new Jurado("","");
		if(entidade instanceof Jurado)
		{
			jurado = (Jurado) entidade;	
		}
		else
		{
			return false;
		}

		/*if (JuradoDAO.jurados.containsKey(jurado.getId())) {
			return JuradoDAO.jurados.put(jurado.getId(), jurado) != null;
		} else {
			return false;
		}*/
		Connection c = getConnection();
		String sqlUsuario = "insert into usuario (usuario,senha)values(?,?);";
		String sqlIdUsuario = "select * from usuario where usuario = ? and senha = ?;";
		String sqlUsuarioPerfil = "insert into re_usuario_perfil (usuario_id_usuario,perfil_id_perfil)values(?,?);";
		String sqlPessoa = "insert into pessoa (usuario_id_usuario, nome, endereco, cpf, cep, telefone, email, sexo)values(?,?,?,?,?,?,?,?);";
		String sqlIdPessoa = "select * from pessoa where cpf = ? and nome = ?;";
		String sqlJurado = "insert into jurado (pessoa_id_pessoa,quesito_id_quesito)values(?,?);";
		try {
			c.setAutoCommit(false); //caso tudo d� errado, isso permite que eu d� rollback nos inserts
			pstmt = c.prepareStatement(sqlUsuario);
			pstmt.setString(1, jurado.getLogin());
			pstmt.setString(2, jurado.getSenha());
			pstmt.execute();
			//Como o commit dessa opera��o s� pode ser feito no final, precisarei fazer a consulta para pegar o id_usuario aqui mesmo
			//Porque estou na mesma transa��o da inser��o na tabela usuario. Eu n�o conseguiria pegar via UsuarioDAO.
			Integer id_usuario = 0;
			pstmt = c.prepareStatement(sqlIdUsuario);
			pstmt.setString(1, jurado.getLogin());
			pstmt.setString(2, jurado.getSenha());
			rs = pstmt.executeQuery();
			while(rs.next()){
				id_usuario = rs.getInt("id_usuario");
			}
			pstmt = c.prepareStatement(sqlUsuarioPerfil);
			pstmt.setInt(1, id_usuario);
			pstmt.setInt(2, Perfil.INTEGRANTE.id);
			pstmt.execute();
			pstmt = c.prepareStatement(sqlPessoa);
			pstmt.setInt(1, id_usuario);
			pstmt.setString(2, jurado.getNome());
			pstmt.setString(3, jurado.getEndereco());
			pstmt.setString(4, jurado.getCpf());
			pstmt.setString(5, jurado.getCep());
			pstmt.setString(6, jurado.getTelefone());
			pstmt.setString(7, jurado.getEmail());
			pstmt.setString(8, jurado.getSexo().sigla);
			pstmt.execute();
			//Mesma coisa aqui para a tabela pessoa
			Integer id_pessoa = 0;
			pstmt = c.prepareStatement(sqlIdPessoa);
			pstmt.setString(1, jurado.getCpf());
			pstmt.setString(2, jurado.getNome());
			rs = pstmt.executeQuery();
			while(rs.next()){
				id_pessoa = rs.getInt("id_pessoa");
			}
			pstmt = c.prepareStatement(sqlJurado);
			pstmt.setInt(1, id_pessoa);
			pstmt.setInt(2, jurado.getQuesitoJulgado().getId());
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
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Jurado jurado = new Jurado("","");
		if(entidade instanceof Jurado)
		{
			jurado = (Jurado) entidade;	
		}
		else
		{
			return false;
		}

		/*
		 * O jurado ainda deve existir no banco por motivos de hist�rico
		 * A exclus�o do usu�rio � pertencente ao escopo do UsuarioDAO, ent�o, este m�todo invoca UsuarioDAO.excluirUsuario
		 * 
		 */
		//return (JuradoDAO.jurados.remove(jurado.getId()) != null);
		return new UsuarioDAO().excluirUsuario(jurado.getLogin(), jurado.getSenha());
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> jurados = new ArrayList<>();
		/*for(int i=0; i< JuradoDAO.jurados.size(); i++ )
		{
			jurados.add(JuradoDAO.jurados.get(i));
		}*/
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, jurado) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = jurado.pessoa_id_pessoa);";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				jurados.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return jurados;
	}

	public List<Entidade> obterTodosAtivos() {
		List<Entidade> jurados = new ArrayList<>();
		/*for(int i=0; i< JuradoDAO.jurados.size(); i++ )
		{
			jurados.add(JuradoDAO.jurados.get(i));
		}*/
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, jurado) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = jurado.pessoa_id_pessoa) where usuario.ativo = true;";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				jurados.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return jurados;
	}

	
	private Jurado resultSet2Object(ResultSet rs) throws SQLException
	{
		Jurado t = new Jurado(rs.getString("usuario"),rs.getString("senha"));
		t.setId(rs.getInt("id_jurado"));
		t.setNome(rs.getString("nome"));
		t.setEndereco(rs.getString("endereco"));
		t.setCpf(rs.getString("cpf"));
		t.setCep(rs.getString("cep"));
		t.setSexo(Sexos.from(rs.getString("sexo")));
		t.setEmail(rs.getString("email"));
		t.setTelefone(rs.getString("telefone"));
		t.setQuesitoJulgado((Quesito) new QuesitoDAO().obterPorId(rs.getInt("quesito_id_quesito")));
		return t;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		//return JuradoDAO.jurados.get(numero);
		Jurado j = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, jurado) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = jurado.pessoa_id_pessoa) where id_jurado = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				j = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return j;
	}

	public Entidade obterAtivoPorId(Integer numero) {
		//return JuradoDAO.jurados.get(numero);
		Jurado j = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, jurado) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = jurado.pessoa_id_pessoa) where usuario.ativo = true and id_jurado = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				j = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return j;
	}

	@Override
	public Collection<Entidade> obterTodosCollection() {
		/*List<Entidade> jurados = new ArrayList<>();
		for(int i=0; i< JuradoDAO.jurados.size(); i++ )
		{
			jurados.add(JuradoDAO.jurados.get(i));
		}
		return jurados;*/
		return this.obterTodos();
	}
}
