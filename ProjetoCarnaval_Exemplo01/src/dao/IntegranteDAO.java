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
import negocio.EscolaSamba;
import negocio.Integrante;
import negocio.Pessoa.Sexos;

public class IntegranteDAO extends AbstractDAO implements DAO
{
	private PreparedStatement pstmt;
	private ResultSet rs;

//	private static Map< Integer, Integrante> integrantes = new HashMap<>();
	
	@Override
	public boolean cadastrar(Entidade entidade) {
		Integrante integrante = new Integrante("","");
		if(entidade instanceof Integrante)
		{
			integrante = (Integrante) entidade;	
		}
		else
		{
			return false;
		}

		/*integrante.setId( IntegranteDAO.integrantes.size() );

		if(IntegranteDAO.integrantes.put( integrante.getId(), integrante ) != null)
		{
			return true;
		}
		else
		{
			return false;
		}*/
		Connection c = getConnection();
		String sqlUsuario = "insert into usuario (usuario,senha)values(?,?);";
		String sqlIdUsuario = "select * from usuario where usuario = ? and senha = ?;";
		String sqlUsuarioPerfil = "insert into re_usuario_perfil (usuario_id_usuario,perfil_id_perfil)values(?,?);";
		String sqlPessoa = "insert into pessoa (usuario_id_usuario, nome, endereco, cpf, cep, telefone, email, sexo)values(?,?,?,?,?,?,?,?);";
		String sqlIdPessoa = "select * from pessoa where cpf = ? and nome = ?;";
		String sqlIntegrante = "insert into integrante (pessoa_id_pessoa)values(?);";
		try {
			c.setAutoCommit(false); //caso tudo d� errado, isso permite que eu d� rollback nos inserts
			pstmt = c.prepareStatement(sqlUsuario);
			pstmt.setString(1, integrante.getLogin());
			pstmt.setString(2, integrante.getSenha());
			pstmt.execute();
			//Como o commit dessa opera��o s� pode ser feito no final, precisarei fazer a consulta para pegar o id_usuario aqui mesmo
			//Porque estou na mesma transa��o da inser��o na tabela usuario. Eu n�o conseguiria pegar via UsuarioDAO.
			Integer id_usuario = 0;
			pstmt = c.prepareStatement(sqlIdUsuario);
			pstmt.setString(1, integrante.getLogin());
			pstmt.setString(2, integrante.getSenha());
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
			pstmt.setString(2, integrante.getNome());
			pstmt.setString(3, integrante.getEndereco());
			pstmt.setString(4, integrante.getCpf());
			pstmt.setString(5, integrante.getCep());
			pstmt.setString(6, integrante.getTelefone());
			pstmt.setString(7, integrante.getEmail());
			pstmt.setString(8, integrante.getSexo().sigla);
			pstmt.execute();
			//Mesma coisa aqui para a tabela pessoa
			Integer id_pessoa = 0;
			pstmt = c.prepareStatement(sqlIdPessoa);
			pstmt.setString(1, integrante.getCpf());
			pstmt.setString(2, integrante.getNome());
			rs = pstmt.executeQuery();
			while(rs.next()){
				id_pessoa = rs.getInt("id_pessoa");
			}
			pstmt = c.prepareStatement(sqlIntegrante);
			pstmt.setInt(1, id_pessoa);
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
	public boolean alterar(Entidade entidade) {
		Integrante integrante = new Integrante("","");
		if(entidade instanceof Integrante)
		{
			integrante = (Integrante) entidade;	
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
			pstmt.setString(1, integrante.getNome());
			pstmt.setString(2, integrante.getEndereco());
			pstmt.setString(3, integrante.getCpf());
			pstmt.setString(4, integrante.getCep());
			pstmt.setString(5, integrante.getTelefone());
			pstmt.setString(6, integrante.getEmail());
			pstmt.setString(7, integrante.getSexo().sigla);
			pstmt.setInt(8, integrante.getId());
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
		
/*		if (IntegranteDAO.integrantes.containsKey(integrante.getId())) {
			return IntegranteDAO.integrantes.put(integrante.getId(), integrante) != null;
		} else {
			return false;
		}*/
	}

	@Override
	public boolean excluir(Entidade entidade) {
		Integrante integrante = new Integrante("","");
		if(entidade instanceof Integrante)
		{
			integrante = (Integrante) entidade;	
		}
		else
		{
			return false;
		}

		/*
		 * O integrante ainda deve existir no banco por motivos de hist�rico
		 * A exclus�o do usu�rio � pertencente ao escopo do UsuarioDAO, ent�o, este m�todo invoca UsuarioDAO.excluirUsuario
		 * 
		 */
		return new UsuarioDAO().excluirUsuario(integrante.getLogin(), integrante.getSenha());
		//return (IntegranteDAO.integrantes.remove(integrante.getId()) != null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> integrantes = new ArrayList<>();
		/*for(int i=0; i< IntegranteDAO.integrantes.size(); i++ )
		{
			integrantes.add(IntegranteDAO.integrantes.get(i));
		}*/
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, integrante) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = integrante.pessoa_id_pessoa);";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				integrantes.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return integrantes;
	}
	
	/*public Set<EscolaSamba> obterTodasEscolasPorIntegrante(Integrante integrante) {
		Set<EscolaSamba> escolas = new HashSet<>();
		
		Connection c = getConnection();
		String sql = "select * from escola_samba join (usuario, integrante) on (usuario.id_usuario = escola_samba.usuario_id_usuario and escola_samba.id_pessoa = integrante.pessoa_id_pessoa);";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				escolas.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return integrantes;
	}*/

	public List<Entidade> obterTodosAtivos() {
		List<Entidade> integrantes = new ArrayList<>();
		/*for(int i=0; i< IntegranteDAO.integrantes.size(); i++ )
		{
			integrantes.add(IntegranteDAO.integrantes.get(i));
		}*/
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, integrante) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = integrante.pessoa_id_pessoa) where usuario.ativo = true;";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				integrantes.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return integrantes;
	}
	

	//Retorna Integrantes pelo nome
	public List<Entidade> obterListaPorNome(String nome,EscolaSamba escola)
	{	
		List<Entidade> integrantes = new ArrayList<>();
		Connection c = getConnection();
		
		String sql = "select * from usuario join (pessoa, integrante, atividade_integrante_escola) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = integrante.pessoa_id_pessoa and integrante.id_integrante = atividade_integrante_escola.integrante_id_integrante) where pessoa.nome like '%?%' and escola_samba_id_escola_samba = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, nome);
			pstmt.setInt(2, escola.getId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				integrantes.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return integrantes;
		
	}

	
	private Integrante resultSet2Object(ResultSet rs) throws SQLException
	{
		Integrante i = new Integrante(rs.getString("usuario"),rs.getString("senha"));
		i.setId(rs.getInt("id_integrante"));
		i.setNome(rs.getString("nome"));
		i.setEndereco(rs.getString("endereco"));
		i.setCpf(rs.getString("cpf"));
		i.setCep(rs.getString("cep"));
		i.setSexo(Sexos.from(rs.getString("sexo")));
		i.setEmail(rs.getString("email"));
		i.setTelefone(rs.getString("telefone"));
		return i;
	}
	
	@Override
	public Entidade obterPorId(Integer numero) {
		//return IntegranteDAO.integrantes.get( numero );
		Integrante i = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, integrante) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = integrante.pessoa_id_pessoa) where id_integrante = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				i = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return i;
	}

	public Entidade obterPorIdUsuario(Integer numero) {
		//return IntegranteDAO.integrantes.get( numero );
		Integrante i = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, integrante) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = integrante.pessoa_id_pessoa) where id_usuario = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				i = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return i;
	}
	
	public Entidade obterAtivoPorId(Integer numero) {
		//return IntegranteDAO.integrantes.get( numero );
		Integrante i = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, integrante) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = integrante.pessoa_id_pessoa) where usuario.ativo = true and id_integrante = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				i = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return i;
	}

	
	@Override
	public Collection<Entidade> obterTodosCollection() {
		/*List<Entidade> integrantes = new ArrayList<>();
		for(int i=0; i< IntegranteDAO.integrantes.size(); i++ )
		{
			integrantes.add(IntegranteDAO.integrantes.get(i));
		}
		return integrantes;*/
		return this.obterTodos();
	}
}
