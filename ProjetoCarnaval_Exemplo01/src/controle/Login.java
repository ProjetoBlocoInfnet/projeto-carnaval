package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Usuario;
import dao.UsuarioDao;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HttpSession session = null;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		session = request.getSession();
		
		Usuario usuario = (Usuario) new UsuarioDao().obterUsuario(login,senha);
		
		
		if (usuario == null){
			System.out.println("usuário inexistente");
			request.setAttribute("resultado", "Usuário inexistente");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{				
			session.setAttribute("usuario", usuario);
			request.setAttribute("usuarioLogado", usuario.getLogin());
			request.setAttribute("usuarioPerfil", usuario.getPerfil());
			switch (usuario.getPerfil()) {
			case ADMINISTRADOR:				
				request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
				break;
			case ESCOLASAMBA:		
				request.getRequestDispatcher("indexEscolaSamba.jsp").forward(request, response);
				break; 	
			case INTEGRANTE:				
				request.getRequestDispatcher("indexIntegrante.jsp").forward(request, response);
				break;
			case TORCEDOR:
				request.getRequestDispatcher("indexTorcedor.jsp").forward(request, response);
				break;
			default:
				break;
			}
							
		}
	}

	
	
	

}
