package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Galera;
import negocio.Usuario;
import dao.GaleraDao;

public class ControlaAcesso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpSession session = null;
	private Usuario logado = null;
       
    public ControlaAcesso() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tela = request.getParameter("tela");
		
		if ("main.jsp".equals(tela)){
			request.setAttribute("user", session.getAttribute("usuarioLogado"));
		} else {
			request.setAttribute("minhaGalera", GaleraDao.obterGalera());
			tela = "index.jsp";
		}
		request.getRequestDispatcher(tela).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession();
		
		logado = new Usuario(request.getParameter("usuario"), request.getParameter("senha"));
		logado.setGalera(new Galera(request.getParameter("galera")));
		
		session.setAttribute("usuarioLogado", logado);
		
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}
}
