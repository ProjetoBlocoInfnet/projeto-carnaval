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
import negocio.Torcedor;
import negocio.Pessoa.Sexos;

public class TorcedorDAO extends AbstractDAO implements DAO
{
	private PreparedStatement pstmt;
	private ResultSet rs;

//	private static Map<Integer, Torcedor> torcedores = new HashMap<>();
	
	/*{
		Torcedor joaozinho = new Torcedor("joaozinho", "senha123");
		EscolaSamba escola = (EscolaSamba) new EscolaSambaDAO().obterPorId(0);
		joaozinho.setNome("Joaozinho das couves");
		joaozinho.setEscolaSamba( escola );
		TorcedorDAO.torcedores.put( 0, joaozinho );
	}*/

	@Override
	public boolean cadastrar(Entidade entidade) {
		Torcedor torcedor = new Torcedor("","");
		if(entidade instanceof Torcedor)
		{
			torcedor = (Torcedor) entidade;	
		}
		else
		{
			return false;
		}

//		torcedor.setId( TorcedorDAO.torcedores.size() );

		//TODO Se estiverem tendo problemas com o cadastrar, coloquem isso aqui
		/*TorcedorDAO.torcedores.put( torcedor.getId(), torcedor );
		if(torcedor.getNome().equals(TorcedorDAO.torcedores.get(torcedor.getId()).getNome()))
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
		String sqlTorcedor = "insert into torcedor (pessoa_id_pessoa,escola_samba_id_escola_samba)values(?,?);";
		try {
			c.setAutoCommit(false); //caso tudo dê errado, isso permite que eu dê rollback nos inserts
			pstmt = c.prepareStatement(sqlUsuario);
			pstmt.setString(1, torcedor.getLogin());
			pstmt.setString(2, torcedor.getSenha());
			pstmt.execute();
			//Como o commit dessa operação só pode ser feito no final, precisarei fazer a consulta para pegar o id_usuario aqui mesmo
			//Porque estou na mesma transação da inserção na tabela usuario. Eu não conseguiria pegar via UsuarioDAO.
			Integer id_usuario = 0;
			pstmt = c.prepareStatement(sqlIdUsuario);
			pstmt.setString(1, torcedor.getLogin());
			pstmt.setString(2, torcedor.getSenha());
			rs = pstmt.executeQuery();
			while(rs.next()){
				id_usuario = rs.getInt("id_usuario");
			}
			pstmt = c.prepareStatement(sqlUsuarioPerfil);
			pstmt.setInt(1, id_usuario);
			pstmt.setInt(2, Perfil.TORCEDOR.id);
			pstmt.execute();
			pstmt = c.prepareStatement(sqlPessoa);
			pstmt.setInt(1, id_usuario);
			pstmt.setString(2, torcedor.getNome());
			pstmt.setString(3, torcedor.getEndereco());
			pstmt.setString(4, torcedor.getCpf());
			pstmt.setString(5, torcedor.getCep());
			pstmt.setString(6, torcedor.getTelefone());
			pstmt.setString(7, torcedor.getEmail());
			pstmt.setString(8, torcedor.getSexo().sigla);
			pstmt.execute();
			//Mesma coisa aqui para a tabela pessoa
			Integer id_pessoa = 0;
			pstmt = c.prepareStatement(sqlIdPessoa);
			pstmt.setString(1, torcedor.getCpf());
			pstmt.setString(2, torcedor.getNome());
			rs = pstmt.executeQuery();
			while(rs.next()){
				id_pessoa = rs.getInt("id_pessoa");
			}
			pstmt = c.prepareStatement(sqlTorcedor);
			pstmt.setInt(1, id_pessoa);
			pstmt.setInt(2, torcedor.getEscolaSamba().getId());
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
		Torcedor torcedor = new Torcedor("","");
		if(entidade instanceof Torcedor)
		{
			torcedor = (Torcedor) entidade;	
		}
		else
		{
			return false;
		}

		/*if (TorcedorDAO.torcedores.containsKey(torcedor.getId())) {
			return TorcedorDAO.torcedores.put(torcedor.getId(), torcedor) != null;
		} else {
			return false;
		}*/
		Connection c = getConnection();
		String sqlUpdate = "update pessoa set nome = ?, endereco = ?, cpf = ?, cep = ?, telefone = ?, email = ?, sexo = ? where id_pessoa in (select id_pessoa from pessoa join (torcedor) on (pessoa.id_pessoa = torcedor.pessoa_id_pessoa) where id_torcedor = ?);";
		try {
			c.setAutoCommit(false);
			pstmt = c.prepareStatement(sqlUpdate);
			pstmt.setString(1, torcedor.getNome());
			pstmt.setString(2, torcedor.getEndereco());
			pstmt.setString(3, torcedor.getCpf());
			pstmt.setString(4, torcedor.getCep());
			pstmt.setString(5, torcedor.getTelefone());
			pstmt.setString(6, torcedor.getEmail());
			pstmt.setString(7, torcedor.getSexo().sigla);
			pstmt.setInt(8, torcedor.getId());
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
		Torcedor torcedor = new Torcedor("","");
		if(entidade instanceof Torcedor)
		{
			torcedor = (Torcedor) entidade;	
		}
		else
		{
			return false;
		}

		/*
		 * O torcedor ainda deve existir no banco por motivos de histórico
		 * A exclusão do usuário é pertencente ao escopo do UsuarioDAO, então, este método invoca UsuarioDAO.excluirUsuario
		 * 
		 */
		//return (TorcedorDAO.torcedores.remove(torcedor.getId()) != null);
		return new UsuarioDAO().excluirUsuario(torcedor.getLogin(), torcedor.getSenha());
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> torcedores = new ArrayList<>();
		/*for(int i=0; i< TorcedorDAO.torcedores.size(); i++ )
		{
			torcedor.add(TorcedorDAO.torcedores.get(i));
		}*/
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, torcedor) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = torcedor.pessoa_id_pessoa);";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				torcedores.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return torcedores;
	}

	public List<Entidade> obterTodosAtivos() {
		List<Entidade> torcedores = new ArrayList<>();
		/*for(int i=0; i< TorcedorDAO.torcedores.size(); i++ )
		{
			torcedor.add(TorcedorDAO.torcedores.get(i));
		}*/
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, torcedor) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = torcedor.pessoa_id_pessoa) where usuario.ativo = true;";
		try {
			pstmt = c.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				torcedores.add(this.resultSet2Object(rs));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		return torcedores;
	}
	
	private Torcedor resultSet2Object(ResultSet rs) throws SQLException
	{
		Torcedor t = new Torcedor(rs.getString("usuario"),rs.getString("senha"));
		t.setId(rs.getInt("id_integrante"));
		t.setNome(rs.getString("nome"));
		t.setEndereco(rs.getString("endereco"));
		t.setCpf(rs.getString("cpf"));
		t.setCep(rs.getString("cep"));
		t.setSexo(Sexos.from(rs.getString("sexo")));
		t.setEmail(rs.getString("email"));
		t.setTelefone(rs.getString("telefone"));
		t.setEscolaSamba((EscolaSamba) new EscolaSambaDAO().obterPorId(rs.getInt("escola_samba_id_escola_samba")));
		return t;
	}
	@Override
	public Entidade obterPorId(Integer numero) {
		//return TorcedorDAO.torcedores.get(numero);
		Torcedor t = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, torcedor) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = torcedor.pessoa_id_pessoa) where id_torcedor = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				t = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return t;
	}

	public Entidade obterAtivoPorId(Integer numero) {
		//return TorcedorDAO.torcedores.get(numero);
		Torcedor t = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, torcedor) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = torcedor.pessoa_id_pessoa) where usuario.ativo = true and id_torcedor = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, numero);
			rs = pstmt.executeQuery();
			while(rs.next()){
				t = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return t;
	}

	
	public Torcedor obterPorNome(String nome)
	{
		/*for(int i=0; i<TorcedorDAO.torcedores.size(); i++)
		{
			if(TorcedorDAO.torcedores.get(i).getNome().toLowerCase().matches("(.*)" + nome.toLowerCase() + "(.*)"))
			{
				return TorcedorDAO.torcedores.get(i);
			}
		}
		return null;*/
		Torcedor t = null;
		Connection c = getConnection();
		String sql = "select * from usuario join (pessoa, torcedor) on (usuario.id_usuario = pessoa.usuario_id_usuario and pessoa.id_pessoa = torcedor.pessoa_id_pessoa) where usuario.ativo = true and nome = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, nome);
			rs = pstmt.executeQuery();
			while(rs.next()){
				t = this.resultSet2Object(rs);
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return t;
	}

	@Override
	public Collection<Entidade> obterTodosCollection() {
		/*List<Entidade> torcedor = new ArrayList<>();
		for(int i=0; i< TorcedorDAO.torcedores.size(); i++ )
		{
			torcedor.add(TorcedorDAO.torcedores.get(i));
		}
		return torcedor;*/
		return this.obterTodos();
	}

}
