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
	
	
	public Usuario obterUsuario(String login, String senha){
			
		Usuario u = null;
		
		Connection c = getConnection();
		String sql = "select id_usuario, usuario, senha, nome_perfil from usuario join (re_usuario_perfil, perfil) on (usuario.id_usuario = re_usuario_perfil.usuario_id_usuario and re_usuario_perfil.perfil_id_perfil = perfil.id_perfil) where usuario.ativo = true and usuario.usuario = ? and usuario.senha = ?";
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, login);
			pstmt.setString(2, senha);
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
