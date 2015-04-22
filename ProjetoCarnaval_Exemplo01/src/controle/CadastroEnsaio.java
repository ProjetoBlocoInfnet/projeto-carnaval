package controle;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EnsaioDAO;
import dao.EscolaSambaDAO;
import negocio.Ensaio;
import negocio.EscolaSamba;


/**
 * Servlet implementation class CadastroEnsaio
 */
public class CadastroEnsaio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroEnsaio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EscolaSamba escola = new EscolaSambaDAO().obterPorNome( request.getParameter( "escola" ));
		Date data = new Date();
		Ensaio ensaio = new Ensaio( escola, data );
		
		new EnsaioDAO().cadastrar( ensaio );
	}

}
