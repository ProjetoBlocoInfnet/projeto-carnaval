package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import negocio.Administrador;
import negocio.Entidade;
import negocio.Atividade;
import negocio.EscolaSamba;
import negocio.Integrante;
import negocio.Torcedor;
import negocio.Usuario;
import enumerator.Acao;
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
		
		torcedor.setEscolaSamba( (EscolaSamba) new EscolaSambaDAO().obterPorId( 0 ) );
		

		Integrante integrante = new Integrante("integrante","123456");
		integrante.setPerfil(Perfil.INTEGRANTE);
		integrante.setNome("Integrante exemplo");
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
		for(Entidade e : new TorcedorDAO().obterTodos()) //Rataria pra aceitar os usuários cadastrados no DAO Fake
		{
			setUsuario.add((Torcedor) e);
		}
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
		String sql = "select usuario, senha, nome_perfil from usuario join (re_usuario_perfil, perfil) on (usuario.id_usuario = re_usuario_perfil.usuario_id_usuario and re_usuario_perfil.perfil_id_perfil = perfil.id_perfil) where usuario.usuario = ? and usuario.senha = ?";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, login);
			pstmt.setString(1, senha);
			rs = pstmt.executeQuery();
			while(rs.next()){
				u = new Usuario(rs.getString("usuario"),rs.getString("senha"));
				u.setPerfil(Perfil.valueOf(rs.getString("nome_perfil")));
			}
		} catch (SQLException e) {
		}finally{
			closeConnection(c);
		}
		
		return u;
	}

}
