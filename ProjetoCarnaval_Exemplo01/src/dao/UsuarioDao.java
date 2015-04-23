package dao;

import java.util.HashSet;
import java.util.Set;

import negocio.Administrador;
import negocio.EscolaSamba;
import negocio.Integrante;
import negocio.Torcedor;
import negocio.Usuario;
import enumerator.Perfil;

public class UsuarioDao {
	
	Set<Usuario> setUsuario = new HashSet<Usuario>();
	{
		Administrador admin = new Administrador("admin","123456");
		admin.setPerfil(Perfil.ADMINISTRADOR);
		admin.setNome("Administrador");
		
		Integrante integrante = new Integrante("integrante","123456");
		integrante.setPerfil(Perfil.INTEGRANTE);
		integrante.setNome("Integrante");
		
		Torcedor torcedor = new Torcedor("torcedor","123456");
		torcedor.setPerfil(Perfil.TORCEDOR);
		torcedor.setNome("Torcedor");
		
		EscolaSamba escola = new EscolaSamba("escolaSamba","123456");
		escola.setPerfil(Perfil.ESCOLASAMBA);
		escola.setNome("Escola de Samba");
		
		setUsuario.add(admin);
		setUsuario.add(integrante);
		setUsuario.add(torcedor);
		setUsuario.add(escola);
		
	}
	
	public Usuario obterUsuario(String login, String senha){
		
		for (Usuario usuario: setUsuario) {
							
			if(usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)){
				return usuario;
			}
		}
		
		return null;
	}

}
