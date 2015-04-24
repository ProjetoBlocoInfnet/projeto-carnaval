package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Usuario;
import dao.UsuarioDAO;

@WebServlet("/login")
public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5480054916620773263L;
	
	private HttpSession session = null;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		session = request.getSession();
		
		Usuario usuario = (Usuario) new UsuarioDAO().obterUsuario(login,senha);
		
		
		if (usuario == null){
			System.out.println("usuário inexistente");
			request.setAttribute("resultado", "Usuário inexistente");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{				
			session.setAttribute("usuario", usuario);
			request.setAttribute("usuarioLogado", usuario.getLogin());
			request.setAttribute("usuarioPerfil", usuario.getPerfil());
			
			request.getRequestDispatcher( usuario.getPerfil().indexPage ).forward(request, response);
		}
	}
}
