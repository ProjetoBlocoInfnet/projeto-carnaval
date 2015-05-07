package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumerator.Perfil;
import negocio.Entidade;
import negocio.Integrante;

public class IntegranteDAO extends AbstractDAO implements DAO
{
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Map< Integer, Integrante> integrantes = new HashMap<>();
	
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
			c.setAutoCommit(false); //caso tudo dê errado, isso permite que eu dê rollback nos inserts
			pstmt = c.prepareStatement(sqlUsuario);
			pstmt.setString(1, integrante.getLogin());
			pstmt.setString(2, integrante.getSenha());
			pstmt.execute();
			//Como o commit dessa operação só pode ser feito no final, precisarei fazer a consulta para pegar o id_usuario aqui mesmo
			//Porque estou na mesma transação da inserção na tabela usuario. Eu não conseguiria pegar via UsuarioDAO.
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

		if (IntegranteDAO.integrantes.containsKey(integrante.getId())) {
			return IntegranteDAO.integrantes.put(integrante.getId(), integrante) != null;
		} else {
			return false;
		}
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
		 * O integrante ainda deve existir no banco por motivos de histórico
		 * A exclusão do usuário é pertencente ao escopo do UsuarioDAO, então, este método invoca UsuarioDAO.excluirUsuario
		 * 
		 */
		return new UsuarioDAO().excluirUsuario(integrante.getLogin(), integrante.getSenha());
		//return (IntegranteDAO.integrantes.remove(integrante.getId()) != null);
	}

	@Override
	public List<Entidade> obterTodos() {
		List<Entidade> integrantes = new ArrayList<>();
		for(int i=0; i< IntegranteDAO.integrantes.size(); i++ )
		{
			integrantes.add(IntegranteDAO.integrantes.get(i));
		}
		return integrantes;
	}

	@Override
	public Entidade obterPorId(Integer numero) {
		// TODO Auto-generated method stub
		return IntegranteDAO.integrantes.get( numero );
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
