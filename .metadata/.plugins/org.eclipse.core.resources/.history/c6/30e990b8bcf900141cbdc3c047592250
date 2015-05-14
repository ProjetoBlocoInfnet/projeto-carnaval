package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import negocio.Usuario;
import enumerator.Perfil;

public class UsuarioDAO extends AbstractDAO
{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/*private static Set<Usuario> setUsuario = new HashSet<Usuario>();
	{
		Administrador admin = new Administrador("admin","123456");
		admin.setPerfil(Perfil.ADMINISTRADOR);
		admin.setNome("Administrador exemplo");
		
		Torcedor torcedor = new Torcedor("torcedor","123456");
		torcedor.setPerfil(Perfil.TORCEDOR);
		torcedor.setNome("Torcedor exemplo");
		new TorcedorDAO().cadastrar( torcedor );
		
		EscolaSamba escola = new EscolaSamba("escolaSamba","123456");
		escola.setPerfil(Perfil.ESCOLASAMBA);
		escola.setNome("Escola de Samba exemplo");
		new EscolaSambaDAO().cadastrar( escola );
		
		EscolaSamba escola2 = new EscolaSamba("escolaSamba2","123456");
		escola2.setPerfil(Perfil.ESCOLASAMBA);
		escola2.setNome("Escola de Samba exemplo2");
		new EscolaSambaDAO().cadastrar( escola2 );
		
		torcedor.setEscolaSamba( (EscolaSamba) new EscolaSambaDAO().obterPorId( 0 ) );
		

		Integrante integrante = new Integrante("integrante","123456");
		integrante.setPerfil(Perfil.INTEGRANTE);
		integrante.setNome("Integrante exemplo");
		integrante.getEscolaSamba().add(escola);
		integrante.getEscolaSamba().add(escola2);
		new IntegranteDAO().cadastrar( integrante );
		
		Atividade atividade = new Atividade();
		atividade.setEscolaSamba( (EscolaSamba) new EscolaSambaDAO().obterPorId( 0 ) );
		atividade.setAcao( Acao.carpinteiro );
		integrante.atividades.add( atividade );
		new AtividadeDAO().cadastrar( atividade );
		
		setUsuario.add(admin);
		setUsuario.add(integrante);
		setUsuario.add(torcedor);;
		setUsuario.add(escola);
<<<<<<< HEAD
		for(Entidade e : new TorcedorDAO().obterTodos()) //Rataria pra aceitar os usu�rios cadastrados no DAO Fake
		{
			setUsuario.add((Torcedor) e);
		}
=======
		setUsuario.add(escola2);
		
>>>>>>> Area Integrante, alteração nas views, controlaAreaIntegrante, EnsaioDAO
	}
	*/
	public Usuario obterUsuario(String login, String senha){
		
		/*for (Usuario usuario: setUsuario) {
							
			if(usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)){
				return usuario;
			}
		}*/
		
		Usuario u = null;
		
		Connection c = getConnection();
		String sql = "select id_usuario, usuario, senha, nome_perfil from usuario join (re_usuario_perfil, perfil) on (usuario.id_usuario = re_usuario_perfil.usuario_id_usuario and re_usuario_perfil.perfil_id_perfil = perfil.id_perfil) where usuario.ativo = true and usuario.usuario = ? and usuario.senha = ?";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, login);
			pstmt.setString(1, senha);
			rs = pstmt.executeQuery();
			while(rs.next()){
				u = new Usuario(rs.getString("usuario"),rs.getString("senha"));
				u.setPerfil(Perfil.from(rs.getString("nome_perfil")));
				u.setId(rs.getInt("id_usuario"));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return u;
	}

	public boolean excluirUsuario(String login, String senha)
	{
		Connection c = getConnection();
		String sql = "update usuario set ativo = false where usuario = ? and senha = ?;";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1,login);
			pstmt.setString(2,senha);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(c);
		}
		
		return true;
	}
}
